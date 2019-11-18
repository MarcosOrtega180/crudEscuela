package formularios;

import conexionSQL.ConexionSQL1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author marcos
 */
public class sistema extends javax.swing.JFrame {
// instancionamos el objeto conexión

    ConexionSQL1 cc = new ConexionSQL1();
    Connection con = cc.conexion(); //importamos la librería conection

    public sistema() {
        initComponents();

        this.setLocationRelativeTo(null); // para que quede en medio
        this.getContentPane().setBackground(getBackground());
        this.mostarDatos();
    }

    //insertado de datos
    public void insertarDatos() {
        try {
            String SQL = "insert into alumnos (nombre, apellidos, materia, calificacion, estatus) values (?,?,?,?,?)";
            PreparedStatement pst = con.prepareStatement(SQL);          
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtApellidos.getText());
            int seleccionado = cbMateria.getSelectedIndex();
            pst.setString(3, cbMateria.getItemAt(seleccionado));
            pst.setDouble(4, Double.parseDouble(txtCalificacion.getText()));
            int seleccionado1 = cbEstatus.getSelectedIndex();
            pst.setString(5, cbEstatus.getItemAt(seleccionado1));
            pst.execute();
            this.mostarDatos();
            JOptionPane.showMessageDialog(null, "Operación exitosa.");

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error durante la operación " + e.getMessage());
        }
    }

    public void actualizarDatos() {
        try {
            String SQL = "update alumnos set nombre=?, apellidos=?, materia=?, calificacion=?, estatus=? where idalumnos = ?";
            int filaSeleccionada = tablaAlumnos.getSelectedRow();
            String dao = (String) tablaAlumnos.getValueAt(filaSeleccionada, 0); //seleccionamos el id
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, txtNombre.getText());
            pst.setString(2, txtApellidos.getText());
            int seleccionado = cbMateria.getSelectedIndex();
            pst.setString(3, cbMateria.getItemAt(seleccionado));
            pst.setDouble(4, Double.parseDouble(txtCalificacion.getText()));
            int seleccionado1 = cbEstatus.getSelectedIndex();
            pst.setString(5, cbEstatus.getItemAt(seleccionado1));
            pst.setString(6, dao);
            pst.execute();
            this.mostarDatos();
            JOptionPane.showMessageDialog(null, "Operación exitosa, registro editado.");

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error durante la edición " + e.getMessage());
        }
    }

    public void eliminarRegistro() {
        int filaSeleccionada = tablaAlumnos.getSelectedRow();
        try {
            String SQL = "delete from alumnos where idalumnos =" + tablaAlumnos.getValueAt(filaSeleccionada, 0);
            Statement st = con.createStatement();
            int n = st.executeUpdate(SQL);
            System.out.println("Este es el resultado de la eliminación" + n);
            if (n >= 0) {
                JOptionPane.showMessageDialog(null, "Registro eliminado con éxito.");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar el registro. "+e.getMessage());
        }
    }

    void limpiarCajas() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCalificacion.setText("");
        cbEstatus.setSelectedItem(null);
        cbMateria.setSelectedItem(null);
    }

    public void mostarDatos() {
        //creamos los titulos de la tabla
        String[] titulos = {"ID", "Nombre", "Apellidos", "Matéria", "Calificación", "Estatus"};
        String[] registros = new String[7];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from alumnos";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            System.out.println("resultSet"+ rs);
            while (rs.next()) {
                registros[0] = rs.getString("idalumnos");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("materia");
                registros[4] = rs.getString("calificacion");
                registros[5] = rs.getString("estatus");
                modelo.addRow(registros);
            }
            tablaAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos" + e.getMessage());
        }
    }
    public void filtrarDatos(String valor) {
        //creamos los titulos de la tabla
        String[] titulos = {"ID", "Nombre", "Apellidos", "Matéria", "Calificación", "Estatus"};
        String[] registros = new String[7];
        DefaultTableModel modelo = new DefaultTableModel(null, titulos);
        String SQL = "select * from alumnos where nombre like ' %"+valor+"%'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                registros[0] = rs.getString("idalumnos");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("materia");
                registros[4] = rs.getString("calificacion");
                registros[5] = rs.getString("estatus");
                modelo.addRow(registros);
            }
            tablaAlumnos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los datos" + e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtCalificacion = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        BtnActualizar = new javax.swing.JButton();
        cbMateria = new javax.swing.JComboBox<>();
        cbEstatus = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaAlumnos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jLabel1.setText("Nombre:");

        jLabel2.setText("Apellidos:");

        jLabel3.setText("Materia:");

        jLabel4.setText("Calificación:");

        jLabel5.setText("Estado:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        BtnActualizar.setText("Actualizar");
        BtnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizarActionPerformed(evt);
            }
        });

        cbMateria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Física", "Laboratorio", "Ingles", "Programación", "Cálculo", "Lenguaje de Programación" }));

        cbEstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aprobado", "Reprobado" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BtnActualizar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCalificacion)
                            .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbMateria, 0, 1, Short.MAX_VALUE)
                            .addComponent(cbEstatus, 0, 99, Short.MAX_VALUE))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cbMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCalificacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnnuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEliminar)
                    .addComponent(BtnActualizar))
                .addGap(98, 98, 98))
        );

        tablaAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaAlumnos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAlumnosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaAlumnos);

        jLabel6.setText("Busqueda:");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(126, 126, 126)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        // TODO add your handling code here: hola
        insertarDatos();
        this.limpiarCajas();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtNombreActionPerformed

    private void tablaAlumnosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAlumnosMouseClicked
        // TODO add your handling code here:
        int filaSeleccionada = tablaAlumnos.rowAtPoint(evt.getPoint());
        txtNombre.setText(tablaAlumnos.getValueAt(filaSeleccionada, 1).toString());
        txtApellidos.setText(tablaAlumnos.getValueAt(filaSeleccionada, 2).toString());
        cbMateria.setSelectedItem(tablaAlumnos.getValueAt(filaSeleccionada, 3));
        txtCalificacion.setText(tablaAlumnos.getValueAt(filaSeleccionada, 4).toString());
        cbEstatus.setSelectedItem(tablaAlumnos.getValueAt(filaSeleccionada, 5));
    }//GEN-LAST:event_tablaAlumnosMouseClicked

    private void BtnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizarActionPerformed
        // TODO add your handling code here:
        this.actualizarDatos();
        this.limpiarCajas();
        this.mostarDatos();
    }//GEN-LAST:event_BtnActualizarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        this.eliminarRegistro();
        this.limpiarCajas();
        this.mostarDatos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        this.filtrarDatos(this.txtBuscar.getText());
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        insertarDatos();
        limpiarCajas();
    }//GEN-LAST:event_btnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JComboBox<String> cbEstatus;
    private javax.swing.JComboBox<String> cbMateria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaAlumnos;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCalificacion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
