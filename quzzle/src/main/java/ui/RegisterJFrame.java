package ui;

import cn.hutool.core.io.FileUtil;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterJFrame extends JFrame implements MouseListener {

    //提升三个输入框的变量的作用范围，让这三个变量可以在本类中所有方法里面可以使用。
    JTextField username = new JTextField();
    JTextField password = new JTextField();
    JTextField rePassword = new JTextField();

    //提升两个按钮变量的作用范围，让这两个变量可以在本类中所有方法里面可以使用。
    JButton submit = new JButton();
    JButton reset = new JButton();
    JDialog jDialog = new JDialog();
    ArrayList<User> allUserInfo = new ArrayList<>();

    public RegisterJFrame() throws IOException {
        readUserInfo();
        initFrame();
        initView();
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*
         * 注册：
         * 用户名密码是否为空
         * 1.先检查两次密码是否一致
         * 2.检查是否已经存在用户名
         * 3.添加用户
         *
         * */

        if (e.getSource() == submit){
            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String resetPasswordInput = rePassword.getText();
            if (usernameInput.length() == 0){
                showDialog("用户名为空");
            }else if (passwordInput.length() == 0 || resetPasswordInput.length() == 0){
                showDialog("密码为空");
            }
            if (passwordInput.equals(resetPasswordInput)){
                System.out.println("密码验证成功");
                //检查是否已经存在用户名
                if (checkUsername(usernameInput)){
                    //3.添加用户
                    try {
                        writeInfo(usernameInput,passwordInput,0);
                        this.setVisible(false);
                        new LoginFrame();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }else{
                showDialog("密码错误");
            }
        }

        if (e.getSource() == reset){
            username.setText("");
            password.setText("");
            rePassword.setText("");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/注册按下.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/重置按下.png"));
        }


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == submit) {
            submit.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/注册按钮.png"));
        } else if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/重置按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    private void initView() {
        //添加注册用户名的文本
        JLabel usernameText = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/注册用户名.png"));
        usernameText.setBounds(85, 135, 80, 20);

        //添加注册用户名的输入框
        username.setBounds(195, 134, 200, 30);

        //添加注册密码的文本
        JLabel passwordText = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/注册密码.png"));
        passwordText.setBounds(97, 193, 70, 20);

        //添加密码输入框
        password.setBounds(195, 195, 200, 30);

        //添加再次输入密码的文本
        JLabel rePasswordText = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/再次输入密码.png"));
        rePasswordText.setBounds(70, 255, 95, 20);

        //添加再次输入密码的输入框
        rePassword.setBounds(195, 255, 200, 30);

        //注册的按钮
        submit.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/注册按钮.png"));
        submit.setBounds(123, 310, 128, 47);
        submit.setBorderPainted(false);
        submit.setContentAreaFilled(false);
        submit.addMouseListener(this);

        //重置的按钮
        reset.setIcon(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/重置按钮.png"));
        reset.setBounds(256, 310, 128, 47);
        reset.setBorderPainted(false);
        reset.setContentAreaFilled(false);
        reset.addMouseListener(this);

        //背景图片
        JLabel background = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/素材/register/background.png"));
        background.setBounds(0, 0, 470, 390);

        this.getContentPane().add(usernameText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(rePasswordText);
        this.getContentPane().add(username);
        this.getContentPane().add(password);
        this.getContentPane().add(rePassword);
        this.getContentPane().add(submit);
        this.getContentPane().add(reset);
        this.getContentPane().add(background);
    }

    private void initFrame() {
        //对自己的界面做一些设置。
        //设置宽高
        setSize(488, 430);
        //设置标题
        setTitle("拼图游戏 V1.0注册");
        //取消内部默认布局
        setLayout(null);
        //设置关闭模式
        setDefaultCloseOperation(3);
        //设置居中
        setLocationRelativeTo(null);
        //设置置顶
        setAlwaysOnTop(true);
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

    public boolean checkUsername(String username){
        for (User user : allUserInfo) {
            if (user.getUsername().equals(username)){
                return false;
            }
        }
        //未发现用户名存在
        return true;
    }

    public static void writeInfo(String username, String password, int count) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/yangyuanchao/Desktop/javaProject/pullzeGame/quzzle/src/allUserInfo",true));
        bw.newLine();
        bw.write("username="+username+"&"+"password="+password+"&"+"count="+count);
        bw.close();
    }
}
