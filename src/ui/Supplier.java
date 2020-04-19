/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.ItemDAO;
import dao.SupplierDAO;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.MyToys;

/**
 *
 * @author TruongTN
 */
public class Supplier extends javax.swing.JPanel {

    Vector supplierList = new Vector();
    Vector headers = new Vector<>();
    Vector<String> allSupCode;
    Vector<String> supCodeUsedList;
    DefaultTableModel tblModel;

    boolean add = true;
    boolean edit = false;
    boolean delete = false;

    public Supplier() {
        initComponents();
        headers.add("Sup code");
        headers.add("Sup Name");
        headers.add("Address");
        headers.add("Supplying");

        tblModel = (DefaultTableModel) this.tblSupplier.getModel();
        loadData();
        loadSupCodeFromSupplier();
        loadSupCodeFromItem();
    }

    public void loadData() {
        try {
            supplierList = new Vector<Object>();
            supplierList = SupplierDAO.loadSupplierList();
            tblModel.setDataVector(supplierList, headers);
            tblSupplier.setModel(tblModel);
            tblSupplier.updateUI();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadSupCodeFromSupplier() {
        try {
            allSupCode = new Vector<>();
            allSupCode = SupplierDAO.loadCodeSupplier();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supplier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loadSupCodeFromItem() {
        try {
            supCodeUsedList = new Vector();
            supCodeUsedList = ItemDAO.getSupCode();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean checkPrimaryKey(String supCode) {
        for (String string : allSupCode) {
            if (string.equals(supCode)) {
                JOptionPane.showMessageDialog(null, "Supplier code must unique");
                return false;
            }
        }
        return true;
    }

    private boolean checkValidaton(String supCode, String supName, String address) {
        String errMsg = "";
        if (!MyToys.checkString(supCode, ".*{5}", 5)) {
            errMsg = "Sup code is from 1 to 5 characters";
            JOptionPane.showMessageDialog(null, errMsg);
            return false;
        }

        if (!MyToys.checkString(supName, ".*{50}", 50)) {
            errMsg = "Sup name is from 1 to 50 characters";
            JOptionPane.showMessageDialog(null, errMsg);
            return false;
        }

        if (!MyToys.checkString(address, ".*{50}", 50)) {
            errMsg = "Max of address length is 50 characters";
            JOptionPane.showMessageDialog(null, errMsg);
            return false;
        }

        return true;
    }

    private boolean checkConstrain(String supCopeDelete) {
        for (String supCode : supCodeUsedList) {
            if(supCode.trim().equals(supCopeDelete)){
                return false;
            }
        }
        return true;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSupplier = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtSupCode = new javax.swing.JTextField();
        txtSupName = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        cbColloborating = new javax.swing.JCheckBox();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        tblSupplier.setModel(new javax.swing.table.DefaultTableModel(
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
        tblSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblSupplierMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblSupplierMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblSupplier);

        jLabel1.setText("Sup Code");

        jLabel2.setText("Sup Name");

        jLabel3.setText("Address");

        jLabel4.setText("Colloborating");

        txtSupCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupCodeActionPerformed(evt);
            }
        });

        btnAddNew.setText("Add New");
        btnAddNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddNewActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbColloborating)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtSupCode)
                                    .addComponent(txtSupName)
                                    .addComponent(txtAddress))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnAddNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(28, 28, 28)
                        .addComponent(btnDelete)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSupCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtSupName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(cbColloborating))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNew)
                            .addComponent(btnSave)
                            .addComponent(btnDelete))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtSupCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupCodeActionPerformed

    }//GEN-LAST:event_txtSupCodeActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        add = true;
        edit = false;
        delete = false;
        this.txtSupCode.setText("");
        this.txtSupCode.setEditable(true);
        this.txtSupCode.setEnabled(true);
        this.txtSupName.setText("");
        this.txtAddress.setText("");
        this.cbColloborating.setSelected(false);
    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (add) {
            String supCode = this.txtSupCode.getText().trim();
            String supName = this.txtSupName.getText().trim();
            String address = this.txtAddress.getText().trim();
            boolean colloborating = false;
            try {
                if (this.cbColloborating.getSelectedObjects().length == 1) {
                    colloborating = true;
                }
            } catch (Exception e) {
            }

            if (checkValidaton(supCode, supName, address) && checkPrimaryKey(supCode)) {
                try {
                    if (SupplierDAO.addNewSupplier(supCode, supName, address, colloborating)) {
                        JOptionPane.showMessageDialog(null, "Add supplier is successful");
                        loadData();
                        btnAddNewActionPerformed(evt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "clgt");
            }
        } else if (edit) {
            String supCode = this.txtSupCode.getText().trim();
            String supName = this.txtSupName.getText().trim();
            String address = this.txtAddress.getText().trim();
            boolean colloborating = false;
            try {
                if (cbColloborating.getSelectedObjects().length == 1) {
                    colloborating = true;
                }
            } catch (Exception e) {
            }
            if (checkValidaton(supCode, supName, address)) {
                try {
                    if (SupplierDAO.updateSupplier(supCode, supName, address, colloborating)) {
                        JOptionPane.showMessageDialog(null, "Update succesful!");
                        loadData();
                        tblModel.setDataVector(supplierList, headers);
                        tblSupplier.updateUI();
                        btnAddNewActionPerformed(evt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblSupplierMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMousePressed
        int row = this.tblSupplier.getSelectedRow();
        if (row >= 0) {
            edit = true;
            delete = true;
            add = false;

            this.txtSupCode.setText(this.tblSupplier.getValueAt(row, 0).toString());
            this.txtSupCode.setEnabled(false);
            this.txtSupName.setText(this.tblSupplier.getValueAt(row, 1).toString());
            this.txtAddress.setText(this.tblSupplier.getValueAt(row, 2).toString());
            this.cbColloborating.setSelected((boolean) this.tblSupplier.getValueAt(row, 3));
        }
    }//GEN-LAST:event_tblSupplierMousePressed

    private void tblSupplierMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSupplierMouseReleased
        if (this.tblSupplier.isEditing()) {
            int row = tblSupplier.getSelectedRow();
            int col = tblSupplier.getSelectedColumn();
            this.tblSupplier.getCellEditor(row, col).cancelCellEditing();
        }

    }//GEN-LAST:event_tblSupplierMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String supCodeDelete = this.txtSupCode.getText().trim();
        if (delete) {
            if (checkConstrain(supCodeDelete)) {
                try {
                    if (SupplierDAO.deleteSupplier(supCodeDelete)) {
                        JOptionPane.showMessageDialog(null, "Delete successful");
                        loadData();
                        btnAddNewActionPerformed(evt);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cannot delete. Violate reference");
            }

        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbColloborating;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSupplier;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtSupCode;
    private javax.swing.JTextField txtSupName;
    // End of variables declaration//GEN-END:variables
}