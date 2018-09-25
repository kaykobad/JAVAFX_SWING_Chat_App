package moodleprojectserver;

/**
 *
 * @author MD_Kaykobad_Reza
 */
import java.io.*;
import java.net.*;
import java.util.*;

public class server_frame extends javax.swing.JFrame 
{
   ArrayList clientOutputStreams;
   ArrayList<String> users;
   Scanner inputFile;

   public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       DataInputStream din;
       DataOutputStream dout;
       Socket sock;
       PrintWriter client;
       PrintWriter toFile;
       String fname;
       int f = 0;

       public ClientHandler(Socket clientSocket, PrintWriter user, DataOutputStream user2) 
       {
            dout = user2;
            client = user;
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
                din = new DataInputStream(sock.getInputStream());
            }
            catch (Exception ex) 
            {
                ta_chat.append("Unexpected error... \n");
            }

       }

       @Override
       public void run() 
       {
            String message, connect = "Connect", disconnect = "Disconnect", chat = "Chat", status=null ;
            String[] data;
            int flag = 0;

            try 
            {
                while ((message = din.readUTF()) != null) 
                {
                    ta_chat.append("Received: " + message + "\n");
                    data = message.split(":");
                    
//                    for (String token:data) 
//                    {
//                        ta_chat.append(token + "\n");
//                    }
                    
                    if(data[0].equals("login") && data[3].equalsIgnoreCase("Student")){
                        
                        inputFile = new Scanner(new File("student.txt"));
                        status = data[3];
                        String uname, pass;
                        while(inputFile.hasNext()){
                            uname = inputFile.next();
                            pass = inputFile.next();
                            
                            if(data[1].equals(uname) && data[2].equals(pass)){
                                //userAdd(data[1]+":"+data[3]);
                                //dout.writeUTF("true");
                                flag = 1;
                            }
                        }
                        if(flag == 0){
                            dout.writeUTF("false");
                        }
                        inputFile.close();
                        
                        inputFile = new Scanner(new File("fullname.txt"));
                        while(inputFile.hasNext()){
                            uname = inputFile.nextLine();
                            String fullname = inputFile.nextLine();
                            
                            if(data[1].equals(uname)){
                                userAdd(fullname+"("+data[3]+")");
                                fname = fullname;
                                f = 1;
                                dout.writeUTF(fullname);
                            }
                        }
                        flag = 0;
                        inputFile.close();
                        ta_chat.append("Verifying login...\n");
                        
                    }
                    
                    else if(data[0].equals("login") && data[3].equalsIgnoreCase("Teacher")){
                        
                        inputFile = new Scanner(new File("teacher.txt"));
                        String uname, pass;
                        while(inputFile.hasNext()){
                            uname = inputFile.next();
                            pass = inputFile.next();
                            
                            if(data[1].equals(uname) && data[2].equals(pass)){
                                //userAdd(data[1]+":"+data[3]);
                                //dout.writeUTF("true");
                                flag = 1;
                            }
                        }
                        if(flag == 0){
                            dout.writeUTF("false");
                        }
                        flag = 0;
                        inputFile.close();
                        
                        inputFile = new Scanner(new File("fullname.txt"));
                        while(inputFile.hasNext()){
                            uname = inputFile.nextLine();
                            String fullname = inputFile.nextLine();
                            
                            if(data[1].equals(uname)){
                                userAdd(fullname+"("+data[3]+")");
                                fname = fullname;
                                f = 2;
                                dout.writeUTF(fullname);
                            }
                        }
                        flag = 0;
                        inputFile.close();
                        ta_chat.append("Verifying login...\n");
                    }
                    
                    else if(data[0].equals("studentlist")){
                        int sL = 1;
                        inputFile = new Scanner(new File("current_student.txt"));
                        String details="", s1, s2, s3, s4, s5, s6, s7;
                        while(inputFile.hasNext()!=false){
                            s1 = inputFile.next(); s2 = inputFile.next(); s3 = inputFile.next();
                            s4 = inputFile.next(); s5 = inputFile.next(); s6 = inputFile.next();
                            s7 = inputFile.next();
                            details +=sL+". "+s1+"\t"+s2+"\t"+s3+"\t"+s4+" "+s5+"\t"+s6+" "+s7+"\n";
                            ++sL;
                        }
                        System.out.println(details);
                        dout.writeUTF(details);
                        inputFile.close();
                        ta_chat.append("Sending student list...\n");
                    }
                    
                    else if(data[0].equals("profile")){
                        inputFile = new Scanner(new File("profile.txt"));
                        String nm, stid, email, ectra, p;
                        while(inputFile.hasNext()){
                            p = inputFile.nextLine();
                            nm = inputFile.nextLine();
                            stid = inputFile.nextLine();
                            email = inputFile.nextLine();
                            ectra = inputFile.nextLine();
                            if(p.equals(data[1])){
                                String prf = nm+"\n"+stid+"\n"+email+"\n"+ectra+"\n";
                                dout.writeUTF(prf);
                                ta_chat.append("sending profile...\n");
                                break;
                            }
                        }
                        inputFile.close();
                        
                    }
                    else if(data[0].equals("post")){
                        if(data[1].equals("ds"))
                            inputFile = new Scanner(new File("ds.txt"));
                        else if(data[1].equals("algo"))
                            inputFile = new Scanner(new File("algo.txt"));
                        else if(data[1].equals("dld"))
                            inputFile = new Scanner(new File("dld.txt"));
                        else if(data[1].equals("machine"))
                            inputFile = new Scanner(new File("machine.txt"));
                        else if(data[1].equals("oop"))
                            inputFile = new Scanner(new File("oop.txt"));
                        else if(data[1].equals("network"))
                            inputFile = new Scanner(new File("network.txt"));
                        
                        String details="";
                        while(inputFile.hasNext()){
                            details +=inputFile.nextLine()+"\n";
                        }
                        inputFile.close();
                        details+="\n";
                        for(int i=2; i<data.length; i++){
                            details += data[i];
                        }
                        System.out.println(details);
                        
                        if(data[1].equals("ds"))
                            toFile = new PrintWriter(new File("ds.txt"));
                        else if(data[1].equals("algo"))
                            toFile = new PrintWriter(new File("algo.txt"));
                        else if(data[1].equals("dld"))
                           toFile = new PrintWriter(new File("dld.txt"));
                        else if(data[1].equals("machine"))
                            toFile = new PrintWriter(new File("machine.txt"));
                        else if(data[1].equals("oop"))
                            toFile = new PrintWriter(new File("oop.txt"));
                        else if(data[1].equals("network"))
                            toFile = new PrintWriter(new File("network.txt"));
                        
                        String s[] = details.split("\n");
                        for(String x: s)
                            toFile.println(x);
                        toFile.flush();
                        toFile.close();
                        dout.writeUTF("true");
                        ta_chat.append("Posting...\n");
                    }
                    
                    else if(data[0].equals("aboutus")){
                        inputFile = new Scanner(new File("about_us.txt"));
                        String details="";
                        while(inputFile.hasNext()){
                            details +=inputFile.nextLine()+"\n";
                        }
                        System.out.println(details);
                        dout.writeUTF(details);
                        inputFile.close();
                        ta_chat.append("Sending about us...\n");
                    }
                    
                    else if(data[0].equals("online")){
                        String onlineNow = "";
                        for(String s:users){
                            onlineNow += s+"\n"; 
                        }
                        dout.writeUTF(onlineNow);
                        ta_chat.append("Sending online user list...\n");
                    }
                    
                    else if(data[0].equals("faculty")){
                        inputFile = new Scanner(new File("faculty.txt"));
                        String details="";
                        while(inputFile.hasNext()){
                            details +=inputFile.nextLine()+"\n";
                        }
                        System.out.println(details);
                        dout.writeUTF(details);
                        inputFile.close();
                        ta_chat.append("Sending faculty list...\n");
                    }
                    
                    else if(data[0].equals("home")){
                        inputFile = new Scanner(new File("home.txt"));
                        String details="";
                        while(inputFile.hasNext()){
                            details +=inputFile.nextLine()+"\n";
                        }
                        System.out.println(details);
                        dout.writeUTF(details);
                        inputFile.close();
                        ta_chat.append("Sending home file...\n");
                    }
                    
                    else if(data[0].equals("course")){
                        if(data[1].equals("ds"))
                            inputFile = new Scanner(new File("ds.txt"));
                        else if(data[1].equals("algo"))
                            inputFile = new Scanner(new File("algo.txt"));
                        else if(data[1].equals("dld"))
                            inputFile = new Scanner(new File("dld.txt"));
                        else if(data[1].equals("machine"))
                            inputFile = new Scanner(new File("machine.txt"));
                        else if(data[1].equals("oop"))
                            inputFile = new Scanner(new File("oop.txt"));
                        else if(data[1].equals("network"))
                            inputFile = new Scanner(new File("network.txt"));
                        
                        String details="";
                        while(inputFile.hasNext()){
                            details +=inputFile.nextLine()+"\n";
                        }
                        //System.out.println(details);
                        inputFile.close();
                        dout.writeUTF(details);
                        ta_chat.append("Sending previous post...\n");
                    }
                    
                    else if(data[0].equals("logout")){
                        userRemove(data[1]+"("+status+")");
                        ta_chat.append("Logout: "+data[1]+"("+status+")"+" Successfull");
                        //dout.writeUTF("true");
                    }
                    
                    else if(data[0].equals("reg")){
                        
                        //writing password and username
                        String write = "";
                        inputFile = new Scanner(new File("student.txt"));
                        while(inputFile.hasNext()){
                            write += inputFile.nextLine();
                            write +="\n";
                        }
                        write += data[1]+"\t"+data[2];
                        inputFile.close();
                        toFile = new PrintWriter(new File("student.txt"));
                        String s[] = write.split("\n");
                        for(String s2: s)
                            toFile.println(s2);
                        toFile.flush();
                        toFile.close();
                        
                        //writing current student
                        String write2 = "";
                        inputFile = new Scanner(new File("current_student.txt"));
                        while(inputFile.hasNext()){
                            write2 += inputFile.nextLine();
                            write2 +="\n";
                        }
                        write2 += data[3]+"\t"+data[5]+"\t"+"Level "+data[6]+"\t"+"Term "+data[7];
                        inputFile.close();
                        toFile = new PrintWriter(new File("current_student.txt"));
                        String s1[] = write2.split("\n");
                        for(String s2: s1)
                            toFile.println(s2);
                        toFile.flush();
                        toFile.close();
                        
                        //writing fullname
                        String write3 = "";
                        inputFile = new Scanner(new File("fullname.txt"));
                        while(inputFile.hasNext()){
                            write3 += inputFile.nextLine();
                            write3 +="\n";
                        }
                        write3 += data[1]+"\n"+data[3];
                        inputFile.close();
                        toFile = new PrintWriter(new File("fullname.txt"));
                        String s3[] = write3.split("\n");
                        for(String s2: s3)
                            toFile.println(s2);
                        toFile.flush();
                        toFile.close();
                        
                        //writing profile
                        String write4 = "";
                        inputFile = new Scanner(new File("profile.txt"));
                        while(inputFile.hasNext()){
                            write4 += inputFile.nextLine();
                            write4 +="\n";
                        }
                        write4 += data[1]+"\n"+"Name: "+data[3]+"\n"+"Student ID: "+data[5]+"\n"+"Email: "+data[4]+"\n"+"Level: "+data[6]+"\t"+"Term: "+data[7];
                        inputFile.close();
                        toFile = new PrintWriter(new File("profile.txt"));
                        String s4[] = write4.split("\n");
                        for(String s2: s4)
                            toFile.println(s2);
                        toFile.flush();
                        toFile.close();
                        
                        ta_chat.append("Regestration successfull...\n");
                    }
                    
                    else if(data[0].equals("comment")){
                        if(data[1].equals("ds"))
                            toFile = new PrintWriter(new File("ds.txt"));
                        else if(data[1].equals("algo"))
                            toFile = new PrintWriter(new File("algo.txt"));
                        else if(data[1].equals("dld"))
                           toFile = new PrintWriter(new File("dld.txt"));
                        else if(data[1].equals("machine"))
                            toFile = new PrintWriter(new File("machine.txt"));
                        else if(data[1].equals("oop"))
                            toFile = new PrintWriter(new File("oop.txt"));
                        else if(data[1].equals("network"))
                            toFile = new PrintWriter(new File("network.txt"));
                        
                        //String p[] = data[2].split("\n");
                        System.out.print(message);
//                        for(String s: p)
//                            toFile.println(s);
                        String s[] = data[2].split("\n");
                        for(String x: s)
                            toFile.println(x);
                        toFile.flush();
                        toFile.close();
                        dout.writeUTF("true");
                        ta_chat.append("Commented successfully...\n");
                    }

                    else 
                    {
                        ta_chat.append("No Conditions were met. \n");
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ta_chat.append("Lost a connection. \n");
                //ex.printStackTrace();
                clientOutputStreams.remove(client);
                if(f==1){
                    userRemove(fname+"(student)");
                    userRemove(fname+"(Student)");
                }
                else if(f==2){
                    userRemove(fname+"(teacher)");
                    userRemove(fname+"(Teacher)");
                }
             } 
	} 
    }

    public server_frame() 
    {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_users = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        lb_name = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server Frame");
        setName("server"); // NOI18N
        setResizable(false);

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        b_start.setText("START");
        b_start.addActionListener((java.awt.event.ActionEvent evt) -> {
            b_startActionPerformed(evt);
        });

        b_end.setText("END");
        b_end.addActionListener((java.awt.event.ActionEvent evt) -> {
            b_endActionPerformed(evt);
        });

        b_users.setText("Online Users");
        b_users.addActionListener((java.awt.event.ActionEvent evt) -> {
            b_usersActionPerformed(evt);
        });

        b_clear.setText("Clear");
        b_clear.addActionListener((java.awt.event.ActionEvent evt) -> {
            b_clearActionPerformed(evt);
        });

        //lb_name.setText("");
        //lb_name.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_start, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 291, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(b_clear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b_users, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_name)
                .addGap(209, 209, 209))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_start)
                    .addComponent(b_users))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_clear)
                    .addComponent(b_end))
                .addGap(4, 4, 4)
                .addComponent(lb_name))
        );

        pack();
    }// </editor-fold>

    //closing all the connections
    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {
        try 
        {
            Thread.sleep(5000);                 //5000 milliseconds is five second.
        } 
        catch(InterruptedException ex) {Thread.currentThread().interrupt();}
        
        tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
        ta_chat.append("Server stopping... \n");
        
        ta_chat.setText("");
    }

    //strats the server
    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {
        Thread starter = new Thread(new ServerStart());
        starter.start();
        
        ta_chat.append("Server started...\n");
    }

    //shows online user list
    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {
        ta_chat.append("\n Online users : \n");
        for (String current_user : users)
        {
            ta_chat.append(current_user);
            ta_chat.append("\n");
        }    
        
    }

    //clears the screen of the server...
    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {
        ta_chat.setText("");
    }
    

    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(() -> {
            new server_frame().setVisible(true);
        });
    }
    
    public class ServerStart implements Runnable 
    {
        @Override
        public void run() 
        {
            clientOutputStreams = new ArrayList();
            users = new ArrayList();  

            try 
            {
                ServerSocket serverSock = new ServerSocket(2222);

                while (true) 
                {
				Socket clientSock = serverSock.accept();
				PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
				clientOutputStreams.add(writer);
                                DataOutputStream dout1 = new DataOutputStream(clientSock.getOutputStream());

				Thread listener = new Thread(new ClientHandler(clientSock, writer, dout1));
				listener.start();
				ta_chat.append("Got a connection. \n");
                }
            }
            catch (Exception ex)
            {
                ta_chat.append("Error making a connection. \n");
            }
        }
    }
    
    //addds newly connected user
    public void userAdd (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        //ta_chat.append("Before " + name + " added. \n");
        users.add(name);
        //ta_chat.append("After " + name + " added. \n");
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
    }
    
    //Removes a connected user
    public void userRemove (String data) 
    {
        String message, add = ": :Connect", done = "Server: :Done", name = data;
        users.remove(name);
        String[] tempList = new String[(users.size())];
        users.toArray(tempList);
    }
    
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
		ta_chat.append("Sending: " + message + "\n");
                writer.flush();
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());

            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
            }
        } 
    }
    
    // Variables declaration - do not modify
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_name;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration
}
