package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.beancontext.BeanContextServiceProvider;
import java.util.Random;
import java.util.stream.IntStream;

public class gameJFrame extends JFrame implements KeyListener, ActionListener {
        private int[][] data = new int[4][4];
        private int x;
        private int y;
        private String path = "/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/animal/animal3/";
        private String bgPath = "/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/background.png";
        private String winPath = "/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/win.png";
        private int number = 0;
        private int[][] win = {
                {1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}
        };
        private JMenuItem replay = new JMenuItem("replay");
        private JMenuItem reLogin = new JMenuItem("reLogin");
        private JMenuItem exit = new JMenuItem("exit");
        private JMenuItem socialAccount  = new JMenuItem("social account");
        private JMenuItem girl = new JMenuItem("美女");
        private JMenuItem animal = new JMenuItem("动物");
        private JMenuItem sports = new JMenuItem("运动");




        // create main frame
        public gameJFrame(){
                initializeFrame();
                initializeMenu();
                initData();
                initPicture();
                countStep();
                this.setVisible(true);

        }
        private void initializeFrame(){
                this.setSize(603, 680);
                this.setTitle("单机拼图游戏");
                this.setAlwaysOnTop(true);
                this.setLocationRelativeTo(null);
                this.setLayout(null);
                this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                this.addKeyListener(this);
        }

        private void initializeMenu(){

//                create menu-bar
                JMenuBar jmenubar = new JMenuBar();

//                create menu
                JMenu jmenuFunction = new JMenu("功能");
                JMenu jmenuAboutUs = new JMenu("About us");
                JMenu changePicture = new JMenu("更换图片");

//                add item to menu
                changePicture.add(girl);
                changePicture.add(animal);
                changePicture.add(sports);
                jmenuFunction.add(changePicture);
                jmenuFunction.add(replay);
                jmenuFunction.add(reLogin);
                jmenuFunction.add(exit);
                jmenuAboutUs.add(socialAccount);


                replay.addActionListener(this);
                exit.addActionListener(this);
                socialAccount.addActionListener(this);
                reLogin.addActionListener(this);
                girl.addActionListener(this);
                animal.addActionListener(this);
                sports.addActionListener(this);

//                add menu to bar
                jmenubar.add(jmenuFunction);
                jmenubar.add(jmenuAboutUs);
//                jmenubar.add(changePicture);
                this.setJMenuBar(jmenubar);
        }

        private void initPicture(){
                this.getContentPane().removeAll();

                if (success()){
                        JLabel winlabel = new JLabel(new ImageIcon(winPath));
                        winlabel.setBounds(203,283,197,73);
                        this.getContentPane().add(winlabel);
                }

                countStep();

                for (int y = 0; y < 4; y++){
                        for (int x = 0; x < 4; x++) {
                                int imgNumber = data[x][y];
                                JLabel jlabel = new JLabel(new ImageIcon(path + imgNumber + ".jpg"));
                                jlabel.setBounds(105 * x + 83,105 * y + 134,105,105);
                                jlabel.setBorder(new BevelBorder (BevelBorder.RAISED));
                                this.getContentPane().add(jlabel);
                        }
                }

//                create background image
                ImageIcon bgi = new ImageIcon(bgPath);
                JLabel bgiLabel = new JLabel(bgi);
                bgiLabel.setBounds(40,40,508,560);
                this.getContentPane().add(bgiLabel);
                this.getContentPane().repaint();
        }

        private void initData(){
                int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
//                create a random array
                Random rn = new Random();
                int tmp;
                for (int i=0; i < array.length; i++){
                        int rnIndex = rn.nextInt(array.length);
                        tmp = array[i];
                        array[i] = array[rnIndex];
                        array[rnIndex] = tmp;
                }

//                add the random array into a 2D array
                for (int i = 0; i < array.length; i++){
                        if (array[i] == 0){
                                x = i / 4;
                                y = i % 4;
                        }
                        data [i / 4][i % 4] =  array[i];

                }
        }

        private boolean success(){
                for (int j = 0; j < data.length; j++) {
                        for (int i = 0; i < data.length; i++) {
                                if (data[i][j] != win[i][j]){
                                        return false;
                                }
                        }
                }
                return true;

        }

        private void countStep(){
                JLabel step = new JLabel("步数: " + number );
                step.setBounds(50,30,100,20);
                this.getContentPane().add(step);
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == 32){
                        this.getContentPane().removeAll();
                        JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
                        all.setBounds(83, 134,420,420);
                        all.setBorder(new BevelBorder (BevelBorder.RAISED));
                        this.getContentPane().add(all);

                        ImageIcon bgi = new ImageIcon(bgPath);
                        JLabel bgiLabel = new JLabel(bgi);
                        bgiLabel.setBounds(40,40,508,560);
                        this.getContentPane().add(bgiLabel);

                        this.getContentPane().repaint();
                }

        }

        @Override
        public void keyReleased(KeyEvent e) {
                int code = e.getKeyCode();
                if (success()){
                        return;
                }

                switch(code){
//                        left
                        case 37:
                                if (x == 0){
                                        return;
                                }
                                System.out.println("left");
                                data[x][y] = data[x-1][y];
                                data[x-1][y] = 0 ;
                                x--;
                                number++;
                                initPicture();
                                break;
                        case 38:
                                if (y == 0){
                                        return;
                                }
                                System.out.println("up");
                                data[x][y] = data [x][y-1];
                                data[x][y-1] = 0;
                                y--;
                                number++;
                                initPicture();
                                break;
                        case 39:
                                if (x == 3){
                                        return;
                                }
                                System.out.println("right");
                                data[x][y] = data [x+1][y];
                                data[x+1][y] = 0;
                                x++;
                                number++;
                                initPicture();
                                break;
                        case 40:
                                if (y == 3) {
                                        return;
                                }
                                System.out.println("down");
                                data[x][y] = data [x][y+1];
                                data[x][y+1] = 0;
                                y++;
                                number++;
                                initPicture();
                                break;
                        case 32:
                                initPicture();
                                break;
                        case 87:
                                data = win;
                                initPicture();
                }

        }


        @Override
        public void actionPerformed(ActionEvent e) {
                Object obj = e.getSource();
                //重新开始
                if (obj == replay){
//                        System.out.println("replay");
                        initData();
                        number = 0;
                        initPicture();

                }
                //退出游戏
                if (obj == exit){
                        System.exit(0);
                }
                //重新登录
                if (obj == reLogin){
                        this.setVisible(false);
                        new LoginFrame();
                }
                //社交账号
                if (obj == socialAccount){
                        JDialog wechat = new JDialog();
                        JLabel jLabel = new JLabel(new ImageIcon("/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/social.png"));
                        jLabel.setBounds(0,0,828,1124);
                        wechat.getContentPane().add(jLabel);
                        wechat.setSize(1000,2000);
                        wechat.setAlwaysOnTop(true);
                        wechat.setLocationRelativeTo(null);
                        wechat.setModal(true);
                        wechat.setVisible(true);
                }
                if (obj == girl){
                        Random rn = new Random();
                        int picNum = rn.nextInt(13) + 1;
                        initData();
                        number = 0;
                        path = "/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/girl/girl" + picNum + "/";
                        initPicture();

                }
                if (obj == animal){
                        Random rn = new Random();
                        int picNum = rn.nextInt(8) + 1;
                        initData();
                        number = 0;
                        path = "/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/animal/animal" + picNum + "/";
                        initPicture();
                }
                if (obj == sports){
                        Random rn = new Random();
                        int picNum = rn.nextInt(10) + 1;
                        initData();
                        number = 0;
                        path = "/Users/yangyuanchao/Desktop/Zhongruan/pullzeGame/quzzle/素材/sport/sport" + picNum + "/";
                        initPicture();
                }

        }
}
