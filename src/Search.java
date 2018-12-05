import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

@SuppressWarnings("serial")
public class Search extends javax.swing.JFrame {

    /**
     * Creates new form Search
     */
    public Search() {
    	//initialise the components
        initComponents();
        
        // call listResource function to display all resources
        listResource();
   }

    
    //connect to database, for testing, change it later to proper database!!!
    public Connection getConnection()
    {
        Connection con = null;
        
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cw230","root","123");
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    
    //return all resources for browsing
    public void listResource()
    {
        Statement st;
        ResultSet rs;
        
        try{
        	DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Title", "Year", "No of Copies"}, 0);
            Connection con = getConnection();
            st = con.createStatement();
            String searchQuery = "SELECT resourceID,title,year,numAvCopies FROM `resource`";
            rs = st.executeQuery(searchQuery);
            
            while(rs.next())
            {
                String a = rs.getString("resourceID");
                String b = rs.getString("title");
                String c = rs.getString("year");
                String d = rs.getString("numAvCopies");
                model.addRow(new Object[]{a, b, c, d});

            	jTable_Results.setModel(model);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
  //return all resources that contain searchString
    public void findResource(String searchString)
    {
        Statement st;
        ResultSet rs;
        
        try{
        	DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Title", "Year", "No of Copies"}, 0);
            Connection con = getConnection();
            st = con.createStatement();
            String searchQuery = "SELECT resourceID,title,year,numAvCopies FROM `resource` WHERE CONCAT(`resourceID`, `title`, `year`) LIKE '%"+searchString+"%'";
            rs = st.executeQuery(searchQuery);
            
            while(rs.next())
            {
                String a = rs.getString("resourceID");
                String b = rs.getString("title");
                String c = rs.getString("year");
                String d = rs.getString("numAvCopies");
                model.addRow(new Object[]{a, b, c, d});

            	jTable_Results.setModel(model);
            }
            
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        btnBook = new javax.swing.JButton();
        btnDvd = new javax.swing.JButton();
        btnLaptop = new javax.swing.JButton();
        jText_Search = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_Results = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });
        
        btnBook.setText("Book");
        btnBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });
        
        btnDvd.setText("DVD");
        btnDvd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });
        
        btnLaptop.setText("Laptop");
        btnLaptop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_SearchActionPerformed(evt);
            }
        });
        
        jTable_Results.setFont(new java.awt.Font("Tahoma", 1, 14));
        jTable_Results.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable_Results);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2Layout.setHorizontalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap(29, Short.MAX_VALUE)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addGroup(jPanel2Layout.createParallelGroup(Alignment.LEADING)
        						.addGroup(jPanel2Layout.createSequentialGroup()
        							.addComponent(btnBook)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnDvd)
        							.addGap(10)
        							.addComponent(btnLaptop))
        						.addGroup(Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
        							.addComponent(jText_Search, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(btnSearch)))
        					.addGap(210))
        				.addGroup(jPanel2Layout.createSequentialGroup()
        					.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 625, GroupLayout.PREFERRED_SIZE)
        					.addGap(29))))
        );
        jPanel2Layout.setVerticalGroup(
        	jPanel2Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel2Layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jText_Search, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
        				.addComponent(btnSearch))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel2Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(btnBook)
        				.addComponent(btnLaptop)
        				.addComponent(btnDvd))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2.setLayout(jPanel2Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE, 683, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>  



    // Buttons to search/filter results
    private void jButton_SearchActionPerformed(java.awt.event.ActionEvent evt) {                                               
     
    	findResource(jText_Search.getText());
       
    }                                              

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Search().setVisible(true);
            }
        });
    }
                
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_Results;
    private javax.swing.JTextField jText_Search;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnDvd;
    private javax.swing.JButton btnBook;
    private javax.swing.JButton btnLaptop;
}
