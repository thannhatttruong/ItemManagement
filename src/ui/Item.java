/*
 * To change this license headers, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import dao.ItemDAO;
import dao.SupplierDAO;
import dto.ItemDTO;
import dto.SupplierDTO;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.MyToys;

/**
 *
 * @author TruongTN
 */
public class Item extends javax.swing.JPanel {

    Vector<ItemDTO> itemList = new Vector<>();
    Vector itemTableView = new Vector();
    Vector<SupplierDTO> codeAndNameOfSupplierList;
    Vector<String> itemCodeList;
    Vector<String> headers = new Vector<>();

    DefaultTableModel tblItemModel = new DefaultTableModel();

    boolean add = true;
    boolean edit = false;
    boolean save = false;
    boolean delete = false;

    public Item() {
        initComponents();
        headers.add("Item code");
        headers.add("Item Name");
        headers.add("Supplier");
        headers.add("Unit");
        headers.add("Price");
        headers.add("Suppling");

        tblItemModel = (DefaultTableModel) this.tblItem.getModel();
        tblItem.setModel(tblItemModel);
        loadCodeAndNameOfSupplierOnCbb();
        loadData();
        loadItemCode();

        tblItem.updateUI();

        showTable();
    }

    public void loadData() {
        try {
            itemList = new Vector();
            itemList = ItemDAO.getItemTable();
            if (itemList == null) {
                System.out.println("null");
            }
        } catch (SQLException ex) {
            System.out.println("Error at loadData() method in Item class _ SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error at loadData() method in Item class _ ClassNotFoundException: " + ex.getMessage());
        }

    }

    public void loadItemCode() {
        itemCodeList = new Vector<>();
        for (ItemDTO itemDTO : itemList) {
            itemCodeList.add(itemDTO.getItemCode());
        }

    }

    public void showTable() {
        itemTableView = new Vector();
        for (ItemDTO itemDTO : itemList) {
            for (SupplierDTO supplierDTO : codeAndNameOfSupplierList) {
                if (itemDTO.getSupCode().equals(supplierDTO.getSupCode())) {
                    Vector vector = new Vector();
                    vector.add(itemDTO.getItemCode());
                    vector.add(itemDTO.getItemName());
                    vector.add(itemDTO.getSupCode() + "-" + supplierDTO.getSupName());
                    vector.add(itemDTO.getUnit());
                    vector.add(itemDTO.getPrice());
                    vector.add(itemDTO.isSupplying());

                    itemTableView.add(vector);
                }
            }
        }
        tblItemModel.setDataVector(itemTableView, headers);
        tblItem.updateUI();
    }

    public void loadCodeAndNameOfSupplierOnCbb() {
        try {
            codeAndNameOfSupplierList = new Vector<>();
            codeAndNameOfSupplierList = SupplierDAO.loadCodeAndNameOfSuppliers();
            this.cbbSupplier.removeAllItems();
            for (SupplierDTO codeAndName : codeAndNameOfSupplierList) {

                this.cbbSupplier.addItem(codeAndName.getSupCode() + "-" + codeAndName.getSupName());
            }
        } catch (SQLException ex) {
            System.out.println("Error at loadCodeAndNameOfSupplierOnCbb() method in Item class _ SQLException: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error at loadCodeAndNameOfSupplierOnCbb() method in Item class _ ClassNotFoundException: " + ex.getMessage());
        }
    }

    private boolean checkPrimaryKey(String itemCode) {
        for (String string : itemCodeList) {
            if (itemCode.trim().equals(string)) {
                JOptionPane.showMessageDialog(null, "Code must unique");
                return false;
            }
        }
        return true;
    }

    private boolean checkValidation(String itemCode, String itemName, String unit, String price) {
        String errorMsg = "";
        if (!MyToys.checkString(itemCode, ".*", 5)) {
            errorMsg = "Max length of code is form 1 to 5 character";
            JOptionPane.showMessageDialog(null, errorMsg);
            return false;
        }

        if (!MyToys.checkString(itemName, ".*", 50)) {
            errorMsg = "Max length of item name is form 1 to 50 character";
            JOptionPane.showMessageDialog(null, errorMsg);
            return false;
        }

        if (!MyToys.checkString(unit, ".*", 10)) {
            errorMsg = "Unit is from 1 to 10 character";
            JOptionPane.showMessageDialog(null, errorMsg);
            return false;
        }

        if (!MyToys.checkString(price, "^[1-9]\\d*", 10)) {
            errorMsg = "Price is from 1 to 10 digits";
            JOptionPane.showMessageDialog(null, errorMsg);
            return false;
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtItemCode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtItemName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbbSupplier = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUnit = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        cbSupplying = new javax.swing.JCheckBox();
        btnAddNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        tblItem.setModel(new javax.swing.table.DefaultTableModel(
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
        tblItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblItemMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblItemMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblItem);

        jLabel1.setText("Item Code");

        txtItemCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtItemCodeActionPerformed(evt);
            }
        });

        jLabel2.setText("Item Name");

        jLabel3.setText("Supplier");

        jLabel4.setText("Unit");

        jLabel5.setText("Price");

        jLabel6.setText("Supplying");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbSupplying)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtItemCode, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                                .addComponent(txtItemName)
                                .addComponent(txtUnit)
                                .addComponent(txtPrice)
                                .addComponent(cbbSupplier, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAddNew)
                        .addGap(18, 18, 18)
                        .addComponent(btnSave)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)))
                .addContainerGap(54, Short.MAX_VALUE))
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
                            .addComponent(txtItemCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cbbSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtUnit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(cbSupplying))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddNew)
                            .addComponent(btnSave)
                            .addComponent(btnDelete))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtItemCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtItemCodeActionPerformed
        loadData();
        loadCodeAndNameOfSupplierOnCbb();
    }//GEN-LAST:event_txtItemCodeActionPerformed

    private void btnAddNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddNewActionPerformed
        add = true;
        edit = false;
        delete = false;
        this.txtItemCode.setText("");
        this.txtItemCode.setEnabled(true);
        this.txtItemName.setText("");
        this.cbbSupplier.setSelectedIndex(0);
        this.txtUnit.setText("");
        this.txtPrice.setText("");
        this.cbSupplying.setSelected(false);

    }//GEN-LAST:event_btnAddNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (add == true) {
            String itemCode = this.txtItemCode.getText().trim();
            String itemName = this.txtItemName.getText().trim();
            String[] supplier = this.cbbSupplier.getSelectedItem().toString().split("-");
            String supCode = supplier[0];
            String unit = this.txtUnit.getText().trim();
            String priceStr = this.txtPrice.getText().trim();
            boolean supplying = false;
            try {
                if (this.cbSupplying.getSelectedObjects().length == 1) {
                    supplying = true;
                }
            } catch (Exception e) {
            }

            if (checkValidation(itemCode, itemName, unit, priceStr) && checkPrimaryKey(itemCode)) {
                int price = Integer.parseInt(priceStr);
                try {
                    if (ItemDAO.addItem(itemCode, itemName, supCode, unit, price, supplying)) {
                        JOptionPane.showMessageDialog(null, "Add item successful");
                        loadData();
                        showTable();
                        loadItemCode();
                        btnAddNewActionPerformed(evt);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error at btnSaveActionPerformed() in Item class _ SQLException: " + ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error at btnSaveActionPerformed() in Item class _ ClassNotFoundException: " + ex.getMessage());
                }
            }
        } else if (edit) {
            String itemCode = this.txtItemCode.getText().trim();
            String itemName = this.txtItemName.getText().trim();
            String[] supplier = this.cbbSupplier.getSelectedItem().toString().split("-");
            String supCode = supplier[0];
            String unit = this.txtUnit.getText().trim();
            String priceStr = this.txtPrice.getText().trim();
            boolean supplying = false;
            try {
                if (this.cbSupplying.getSelectedObjects().length == 1) {
                    supplying = true;
                }
            } catch (Exception e) {
            }

            if (checkValidation(itemCode, itemName, unit, priceStr)) {
                try {
                    int price = Integer.parseInt(priceStr);
                    if (ItemDAO.updateItem(itemCode, itemName, supCode, unit, price, supplying)) {
                        JOptionPane.showMessageDialog(null, "Edit is sucessful");
                        loadData();
                        showTable();
                        btnAddNewActionPerformed(evt);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error at btnSaveActionPerformed() in Item class _ SQLException: " + ex.getMessage());
                } catch (ClassNotFoundException ex) {
                    System.out.println("Error at btnSaveActionPerformed() in Item class _ ClassNotFoundException: " + ex.getMessage());
                }

            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void tblItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMousePressed
        int row = this.tblItem.getSelectedRow();
        if (row >= 0) {
            edit = true;
            delete = true;
            add = false;

            this.txtItemCode.setText(this.tblItem.getValueAt(row, 0).toString());
            this.txtItemCode.setEnabled(false);
            this.txtItemName.setText(this.tblItem.getValueAt(row, 1).toString());
            this.cbbSupplier.setSelectedItem(this.tblItem.getValueAt(row, 2));
            this.txtUnit.setText(this.tblItem.getValueAt(row, 3).toString());
            this.txtPrice.setText(this.tblItem.getValueAt(row, 4).toString());
            this.cbSupplying.setSelected((boolean) this.tblItem.getValueAt(row, 5));
        }

    }//GEN-LAST:event_tblItemMousePressed

    private void tblItemMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblItemMouseReleased
        if (tblItem.isEditing()) {
            int row = this.tblItem.getSelectedRow();
            int col = this.tblItem.getSelectedColumn();
            tblItem.getCellEditor(row, col).cancelCellEditing();
        }
    }//GEN-LAST:event_tblItemMouseReleased

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        String itemCode = this.txtItemCode.getText().trim();

        if (delete) {
            try {
                if (ItemDAO.deleteItem(itemCode)) {
                    JOptionPane.showMessageDialog(null, "Delete sucessful");

                    loadData();
                    loadItemCode();
                    showTable();
                    btnAddNewActionPerformed(evt);
                } else {
                    JOptionPane.showMessageDialog(null, "Choose row to delete");
                }
            } catch (SQLException ex) {
                System.out.println("Error at btnDeleteActionPerformed() in Item class _ SQLException: " + ex.getMessage());
            } catch (ClassNotFoundException ex) {
                System.out.println("Error at btnDeleteActionPerformed() in Item class _ ClassNotFoundException: " + ex.getMessage());
            }
        }

    }//GEN-LAST:event_btnDeleteActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked

    }//GEN-LAST:event_formMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddNew;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbSupplying;
    private javax.swing.JComboBox<String> cbbSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblItem;
    private javax.swing.JTextField txtItemCode;
    private javax.swing.JTextField txtItemName;
    private javax.swing.JTextField txtPrice;
    private javax.swing.JTextField txtUnit;
    // End of variables declaration//GEN-END:variables
}
