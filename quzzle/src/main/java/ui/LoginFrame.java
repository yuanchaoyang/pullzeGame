package ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.Random;



public class LoginFrame extends JFrame implements ActionListener {
        private JButton loginButton = new JButton();
        private JButton registerButton = new JButton();

        private final String name = "owen";
        private final int realPassword = 123;

        private JTextField userName = new JTextField();
        private JTextField password = new JTextField();


        public LoginFrame(){
                initializeFrame();
                setUsername();
                setPassword();
                setVarify();
                setLoginButton();
                setRegisterButton();
                setBackgroundImage();
                this.setVisible(true);
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

                JTextField password = new JTextField();
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

                JTextField code = new JTextField();
                code.setBounds(195,256,100,30);
                this.getContentPane().add(code);

                String codeStr = getCode(4);
                System.out.println("Generated Code: " + codeStr);
                JLabel rightCode = new JLabel();
                rightCode.setText(codeStr);
                rightCode.setBounds(300,256,50,30);
                this.getContentPane().add(rightCode);
        }

        private void setLoginButton(){

                loginButton.setBounds(123,310,128,47);
                loginButton.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/login/登录按钮.png"));
                loginButton.setBorderPainted(false);
                loginButton.setContentAreaFilled(false);
                this.getContentPane().add(loginButton);

                //
                loginButton.addActionListener(this);
        }

        private void setRegisterButton(){
                registerButton.setBounds(256,310,128,47);
                registerButton.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/login/注册按钮.png"));
                registerButton.setBorderPainted(false);
                registerButton.setContentAreaFilled(false);
                this.getContentPane().add(registerButton);
        }

        private void setBackgroundImage(){
                JLabel bg = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/login/background.png"));
                bg.setBounds(0,0,470,390);
                this.getContentPane().add(bg);
        }


        @Override
        public void actionPerformed(ActionEvent a) {
                Object obj = a.getSource();

                if (obj == loginButton){
                        System.out.println("login");
                }

                if (obj == registerButton){
                        System.out.println("register");
                }
        }
}
