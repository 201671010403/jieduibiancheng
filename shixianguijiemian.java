package a.a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;
public class shixianguijiemian {
    private JFrame frame;//����Jframe���
    private FileDialog openFile;//ģʽ�Ի���
    private JTextArea area;//�ı���
    private JButton button;
    private JButton button1;
    private JLabel label;
    PreparedStatement psql;//
    HashMap<String, Integer> hashMap=null;
    public shixianguijiemian() {
        init();
        addEvents();
    }
    public void init() {
        //new���˵��Ķ���
        frame = new JFrame("�˵�����");
        frame.setBounds(500, 400, 400, 400);
        frame.setLayout(null);
        button = new JButton("ѡ���ļ�");
        button.setBounds(120, 30, 100, 40);
        button1 = new JButton("ִ��ѡ��");
        button1.setBounds(120, 100, 100, 40);
        //�ı���
        area = new JTextArea(10,5);
        area.setLineWrap(true);
        area.setBounds(20,200,360,100);
        label=new JLabel("ִ�н��:");
        label.setBounds(140,150,100,40);
        frame.add(button);
        frame.add(button1);
        frame.add(area);
        frame.add(label);
        frame.setVisible(true);
    }
    //�رմ���
    public void addEvents() {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //���ļ�
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //����һ���ļ��Ի���
                openFile = new FileDialog(frame, "���ļ�", FileDialog.LOAD);
                openFile.setVisible(true);
                //�õ��ļ���Ϣ
                String dirName = openFile.getDirectory();//����FileDialog�����getDirectory()�������õ�String��Ŀ¼
                String fileName = openFile.getFile();//����FileDialog�����getFile()�������õ�String���ļ�����
                //��ȡչʾ�ļ�
                if (dirName == null || fileName == null) {
                    return;
                }
                File file = new File(dirName, fileName);
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));
                    String line;
                    StringBuilder text = new StringBuilder();//����ļ�����
                    while ((line = br.readLine()) != null) {
                        text.append(line);
                    }
                    String spilrStr1 = String.valueOf(text);
                    //����","�ָ���ַ�����(��".")
                    String[] word1 = spilrStr1.split(",");
                    //�ַ�����ת��Ϊ�ַ���
                    String spilrStr2 = StringUtils.join(word1);
                    //��"."�ָ��ַ�����(ֻʣ�ո�)
                    String[] word2 = spilrStr2.split("\\.");
                    //ת��Ϊ�ַ���
                    String spilrStr3 = StringUtils.join(word2);
                    //���ݿո�ֿ�
                    String[] word3 = spilrStr3.split(" ");
                    String spilrStr4 = StringUtils.join(word3, " ");
                    //���ֵ����
                    String[] sortword = Sort(word3);
                    //ͳ�Ƶ��ʳ��ֵ�Ƶ��
                    HashMap<String, Integer> map = new HashMap();
                    for (String str : sortword) {
                        if (!map.containsKey(str)) {//��str������,
                            map.put(str, 1);
                        } else {
                            //������c��ֵ���Ҽ�1
                            map.put(str, map.get(str) + 1);
                        }
                        //System.out.println(str + "���ֵĴ���Ϊ:" + map.get(str)+"��");
                    }
                    hashMap=map;
//                    //���д���ļ�
//                    String filestr = String.valueOf(map);
//                    BufferedWriter writer = new BufferedWriter(new FileWriter("D://out.txt"));
//                    writer.write(filestr);
//                    writer.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        button1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //д�����ݿ�
                Conn conn = new Conn();
                Connection connection = null;
                try {
                    connection = conn.getCon();
                    System.out.println("���ݿ����ӳɹ�");
                    for (String key : hashMap.keySet()) {
                        psql = connection.prepareStatement("insert into words(wordName,counts) values(?,?)");
                        psql.setString(1, key);
                        psql.setInt(2, hashMap.get(key));
                        psql.executeUpdate();
                    }
                    psql.close();
                    connection.close();
                    System.out.println("���ݿ��ųɹ�!");
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                String str= String.valueOf(hashMap.toString());
                String[] str1=str.split(",");
                String str2 = StringUtils.join(str1, " ");
                area.setText(str2);
            }
        });
    }
    //���ֵ����
    public String[] Sort(String[] word3) {
        String temp;
        for (int i = 0; i < word3.length; i++) {
            for (int j = word3.length - 1; j > i; j--) {
                if (word3[j - 1].compareTo(word3[j]) > 0) {
                    temp = word3[j - 1];
                    word3[j - 1] = word3[j];
                    word3[j] = temp;
                }
            }
        }
        return word3;
    }
    public static void main(String[] args) {
        new shixianguijiemian();
    }
}