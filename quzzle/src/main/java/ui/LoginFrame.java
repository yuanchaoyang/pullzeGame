package ui;

import cn.hutool.core.io.FileUtil;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.Random;



public class LoginFrame extends JFrame implements ActionListener {
        private JButton loginButton = new JButton();
        private JButton registerButton = new JButton();

        private JTextField userName = new JTextField();
        private JTextField password = new JTextField();
        private JTextField code = new JTextField();
        private ArrayList<User> allUserInfo = new ArrayList<>();
        private String codeStr;
        private JLabel rightCode = new JLabel();


        public LoginFrame() throws IOException {
                readUserInfo();
                initializeFrame();
                setUsername();
                setPassword();
                setVarify();
                setLoginButton();
                setRegisterButton();
                setBackgroundImage();
                this.setVisible(true);
        }

        private void readUserInfo() throws IOException {
                //读取用户信息
                List<String> lines = FileUtil.readUtf8Lines("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/src/allUserInfo");

                //把读取的信息创建 user 对象
                for (String line : lines) {
                        String[] parts = line.split("&");
                        String username = parts[0].split("=")[1];
                        String password = parts[1].split("=")[1];
                        int count = Integer.parseInt(parts[2].split("=")[1]);
                        allUserInfo.add(new User(username,password,count));
                }
        }

        private void initializeFrame(){
                this.setSize(488,430);
                this.setTitle("登录");
                this.setAlwaysOnTop(true);
                this.setLocationRelativeTo(null);
                this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        private void setUsername(){
                JLabel userNameText = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/login/用户名.png"));
                userNameText.setBounds(116,135,47,17);
                this.getContentPane().add(userNameText);


               userName.setBounds(195,134,200,30);
               this.getContentPane().add(userName);
        }

        private void setPassword(){
                JLabel passwordtext = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/login/密码.png"));
                passwordtext.setBounds(130,195,32,16);
                this.getContentPane().add(passwordtext);

                password.setBounds(195,195,200,30);
                this.getContentPane().add(password);
        }

        public static String getCode(int length) {
                String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                StringBuilder code = new StringBuilder();
                Random random = new Random();

                for (int i = 0; i < length; i++) {
                        code.append(characters.charAt(random.nextInt(characters.length())));
                }

                return code.toString();
        }

        private void setVarify(){
                JLabel varify = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/login/验证码.png"));
                varify.setBounds(133,256,50,30);
                this.getContentPane().add(varify);

                code.setBounds(195,256,100,30);
                this.getContentPane().add(code);

                codeStr = getCode(4);
                System.out.println("Generated Code: " + codeStr);

                rightCode.setText(codeStr);
                rightCode.setBounds(300,256,50,30);
                this.getContentPane().add(rightCode);
        }

        private void setLoginButton(){

                loginButton.setBounds(123,310,128,47);
                loginButton.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/login/登录按钮.png"));
                loginButton.setBorderPainted(false);
                loginButton.setContentAreaFilled(false);
                this.getContentPane().add(loginButton);
                loginButton.addActionListener(this);
        }

        private void setRegisterButton(){
                registerButton.setBounds(256,310,128,47);
                registerButton.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/login/注册按钮.png"));
                registerButton.setBorderPainted(false);
                registerButton.setContentAreaFilled(false);
                this.getContentPane().add(registerButton);
                registerButton.addActionListener(this);
        }

        private void setBackgroundImage(){
                JLabel bg = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/login/background.png"));
                bg.setBounds(0,0,470,390);
                this.getContentPane().add(bg);
        }


        @Override
        public void actionPerformed(ActionEvent a) {
                Object obj = a.getSource();

                //点击登录按钮
                if (obj == loginButton){
                        String usernameInput = userName.getText();
                        String passwordInput = password.getText();
                        int count = 0;
                        User user = new User(usernameInput,passwordInput, count);
                        System.out.println(user);
                        System.out.println(allUserInfo);
                        if ((code.getText().length() == 0)){
                                showDialog("验证码为空");
                                //刷新验证码
                                refreshVerificationCode();
                        }else if (usernameInput.length() == 0 || passwordInput.length() == 0){
                                showDialog("用户名或密码为空");
                                //刷新验证码
                                refreshVerificationCode();
                        } else if (code.getText().equalsIgnoreCase(codeStr)) {
                                if ( checkUserinfo(user) ){
                                        System.out.println("登录成功");
                                        this.setVisible(false);
                                        new gameJFrame();
                                }else{
                                        showDialog("用户名密码错误");
                                        //刷新验证码
                                        refreshVerificationCode();
                                }
                        }else {
                                showDialog("验证码错误");
                                //刷新验证码
                                refreshVerificationCode();
                        }
                }

                //点击注册按钮
                if (obj == registerButton){
                        System.out.println("register");
                        this.setVisible(false);
                        new RegisterJframe();
                }
        }

        public boolean checkUserinfo( User user){
                for (User all : allUserInfo) {
                        if ( (all.getUsername().equals(user.getUsername())) && (all.getPassword().equals(user.getPassword()))){
                                return true;
                        }
                }
                return false;

        }

        public void showDialog(String s) {
                JDialog dialog = new JDialog(this,  true);
                dialog.setSize(200, 150);
                dialog.setLocationRelativeTo(this); // Center the dialog

                // Add a simple label to the dialog
                JLabel messageLabel = new JLabel(s, SwingConstants.CENTER);
                dialog.add(messageLabel, BorderLayout.CENTER);

                // Add a button to close the dialog
                JButton closeButton = new JButton("关闭");
                closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                dialog.dispose();
                        }
                });
                dialog.add(closeButton, BorderLayout.SOUTH);

                dialog.setVisible(true);
        }

        private void refreshVerificationCode() {
                codeStr = getCode(4);
                rightCode.setText(codeStr);
                System.out.println("New Generated Code: " + codeStr);
        }
}
