/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package br.com.ddr21.Telas;

/**
 *
 * @author Maxswell Diniz
 */
// importando os dados para fazer as conexões
import java.sql.*;
import br.com.ddr21.dal.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {
    // declarando variáveis para usar os metodos
    // puxando os comandos no import java,sql.*;
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Esse trecho de código é o construtor do módulo de conexão
     */
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    // criando metodo Pesquisa para guardar a consulta nessa variável
    private void pesquisar() {
        String sql = "select * from usuarios where iduser=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            rs = pst.executeQuery();
            // caso tenha usuario cadastrado o comando abaixo vai preencher os campos
            if (rs.next()) {
                txtUsoNome.setText(rs.getString(2));
                txtUsuFone.setText(rs.getString(3));
                txtUsoLogin.setText(rs.getString(4));
                txtUsoSenha.setText(rs.getString(5));
                // prenchendo o combox perfil
                cboUsoPerfil.setSelectedItem(rs.getString(6));

            } else {
                // caso não tenha um Id cadastrado mostre uma mensagem
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // Limpando os campos após o usuário não cadastrado
                // setando todos os campos como null vazio
                txtUsoNome.setText(null);
                txtUsuFone.setText(null);
                txtUsoLogin.setText(null);
                txtUsoSenha.setText(null);
              //  cboUsoPerfil.setSelectedItem(null);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  // FIM DO CAMPO PESQUISAR USUÁRIO ✅
    }
    // MÉTODO PARA ADICIONAR USUÁRIOS

    private void adicionar() {
        String sql = "insert into usuarios (iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuId.getText());
            pst.setString(2, txtUsoNome.getText());
            pst.setString(3, txtUsuFone.getText());
            pst.setString(4, txtUsoLogin.getText());
            pst.setString(5, txtUsoSenha.getText());
            pst.setString(6, cboUsoPerfil.getSelectedItem().toString());
            //VALIDAÇÃO DOS CAMPOS OBRIGATÓRIOS
            if ((txtUsuId.getText().isEmpty())||(txtUsoNome.getText().isEmpty())||(txtUsoLogin.getText().isEmpty())||(txtUsoSenha.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatórios");

            } else {

                //ATUALIZANDO A TABELA USUÁRIO COM OS DADOS DO FORMULÁRIO         
                // CRIANDO UMA CAIXA DE DIÁLOGO PARA O USUÁRIO SE O VALOR FOR MAIOR QUE ZERO EXIBA UMA MENSAGEM
                int adicionado = pst.executeUpdate();
                // A LINHA ABAIXO SERVE DE APOIO A LÓGICA VAI MOSTRA 1 NO TERMINAL
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Suceso");
                    //COMANDO ABAIXO É PARA LIMPAR OS CAMPOS APÓS UMA AÇÃO DE CADASTRO
                    txtUsuId.setText(null);
                    txtUsoNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                    //cboUsoPerfil.setSelectedItem(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    // CRIANDO O METODO ATUALIZAR DADOS DE USUÁRIO CADASTRADO
        private void atualizar(){
            String sql = "update  usuarios set usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsoNome.getText());
             pst.setString(2, txtUsuFone.getText());
              pst.setString(3, txtUsoLogin.getText());
               pst.setString(4, txtUsoSenha.getText());
               pst.setString(5, cboUsoPerfil.getSelectedItem().toString());
               pst.setString(6, txtUsuId.getText());
               
               //VALIDAÇÃO DOS CAMPOS OBRIGATÓRIOS
               if ((txtUsuId.getText().isEmpty())||(txtUsoNome.getText().isEmpty())||(txtUsoLogin.getText().isEmpty())||(txtUsoSenha.getText().isEmpty())){
                JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatórios");

            } else {

                //ATUALIZANDO A TABELA USUÁRIO COM OS DADOS DO FORMULÁRIO         
                // CRIANDO UMA CAIXA DE DIÁLOGO PARA O ALTERAR O USUÁRIO SE O VALOR FOR MAIOR QUE ZERO EXIBA UMA MENSAGEM
                int adicionado = pst.executeUpdate();
                // A LINHA ABAIXO SERVE DE APOIO A LÓGICA VAI MOSTRA 1 NO TERMINAL
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Atualizado com Suceso");
                    //COMANDO ABAIXO É PARA LIMPAR OS CAMPOS APÓS UMA AÇÃO DE CADASTRO
                    txtUsuId.setText(null);
                    txtUsoNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                    //cboUsoPerfil.setSelectedItem(null);
                }
               }
                                
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, e);
            }

}
        // METODO RESPONSÁVEL PELA REMOÇÃO DE USUÁRIOS
        private void remover(){
            // ESTRUTURA ABAIXO CONFIRMA A REMOÇÃO DO USUÁRIO
            int confirma=JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover este usuário","Atenção",JOptionPane.YES_NO_OPTION);
            if (confirma==JOptionPane.YES_OPTION){
                String sql="delete from usuarios where iduser=?";
                try {
                    pst = conexao.prepareStatement(sql);
                    pst.setString(1,txtUsuId.getText());
                    
                    // CRIANDO UMA ESTRUTURA DE ESCOLHA
                    int apagado = pst.executeUpdate();
                    if(apagado>0){
                    JOptionPane.showMessageDialog(null,"Usuário removido com Sucesso");
                     //COMANDO ABAIXO É PARA LIMPAR OS CAMPOS APÓS UMA AÇÃO DE CADASTRO
                    txtUsuId.setText(null);
                    txtUsoNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsoLogin.setText(null);
                    txtUsoSenha.setText(null);
                    //cboUsoPerfil.setSelectedItem(null);
                    }
                    
                    
                } catch (Exception e) {
                     JOptionPane.showMessageDialog(null, e);
                }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuId = new javax.swing.JTextField();
        txtUsoNome = new javax.swing.JTextField();
        txtUsuFone = new javax.swing.JTextField();
        cboUsoPerfil = new javax.swing.JComboBox<>();
        txtUsoLogin = new javax.swing.JTextField();
        txtUsoSenha = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnUsuCreate = new javax.swing.JButton();
        brtUsoRead = new javax.swing.JButton();
        btnUsoConsultar = new javax.swing.JButton();
        btnUsoDelete = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuários");
        setPreferredSize(new java.awt.Dimension(436, 454));

        jLabel1.setText("* ID");

        jLabel2.setText("* Nome");

        jLabel3.setText("* Login");

        jLabel4.setText("Fone");

        jLabel5.setText("* Perfil");

        cboUsoPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "admin", "user" }));
        cboUsoPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboUsoPerfilActionPerformed(evt);
            }
        });

        jLabel6.setText("* Senha");

        btnUsuCreate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ddr21/Icones/create.png"))); // NOI18N
        btnUsuCreate.setToolTipText("adicionar");
        btnUsuCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsuCreate.setPreferredSize(new java.awt.Dimension(80, 80));
        btnUsuCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuCreateActionPerformed(evt);
            }
        });

        brtUsoRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ddr21/Icones/update.png"))); // NOI18N
        brtUsoRead.setToolTipText("Editar");
        brtUsoRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        brtUsoRead.setPreferredSize(new java.awt.Dimension(75, 75));
        brtUsoRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brtUsoReadActionPerformed(evt);
            }
        });

        btnUsoConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ddr21/Icones/pesquisar.png"))); // NOI18N
        btnUsoConsultar.setToolTipText("Consultar");
        btnUsoConsultar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoConsultar.setPreferredSize(new java.awt.Dimension(75, 75));
        btnUsoConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoConsultarActionPerformed(evt);
            }
        });

        btnUsoDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/ddr21/Icones/delete.png"))); // NOI18N
        btnUsoDelete.setToolTipText("Deletar");
        btnUsoDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUsoDelete.setPreferredSize(new java.awt.Dimension(75, 75));
        btnUsoDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsoDeleteActionPerformed(evt);
            }
        });

        jLabel7.setText("* Campos Obrigatórios");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(208, 208, 208)
                                .addComponent(txtUsoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 15, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(34, 266, Short.MAX_VALUE)
                                .addComponent(btnUsoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(cboUsoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtUsoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(171, 171, 171)
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(txtUsoNome)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnUsoConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(brtUsoRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)))
                        .addGap(15, 15, 15)))
                .addGap(32, 32, 32))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {brtUsoRead, btnUsoConsultar, btnUsoDelete, btnUsuCreate});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsoLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsoSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(cboUsoPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(79, 79, 79)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnUsoDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brtUsoRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsuCreate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUsoConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {brtUsoRead, btnUsoConsultar, btnUsoDelete, btnUsuCreate});

        setBounds(0, 0, 495, 454);
    }// </editor-fold>//GEN-END:initComponents

    private void brtUsoReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brtUsoReadActionPerformed
        // EVENTO PARA EDITAR OS DADOS
        atualizar();
    }//GEN-LAST:event_brtUsoReadActionPerformed

    private void cboUsoPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboUsoPerfilActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboUsoPerfilActionPerformed

    private void btnUsoConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoConsultarActionPerformed
        // Chamando o evento ao clicar no botão pesquisar
        pesquisar();
    }//GEN-LAST:event_btnUsoConsultarActionPerformed

    private void btnUsuCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuCreateActionPerformed
        // CRIANDO O EVENTO AO CLICAR NO BOTÃO ADICIONAR OU CADASTRAR
        adicionar();


    }//GEN-LAST:event_btnUsuCreateActionPerformed

    private void btnUsoDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsoDeleteActionPerformed
        // CHAMANDO O EVENTO PARA DELETAR DADOS DO USUÁRIO CADASTRADO
        remover();
        
    }//GEN-LAST:event_btnUsoDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brtUsoRead;
    private javax.swing.JButton btnUsoConsultar;
    private javax.swing.JButton btnUsoDelete;
    private javax.swing.JButton btnUsuCreate;
    private javax.swing.JComboBox<String> cboUsoPerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField txtUsoLogin;
    private javax.swing.JTextField txtUsoNome;
    private javax.swing.JTextField txtUsoSenha;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuId;
    // End of variables declaration//GEN-END:variables

    private void Adicionar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void editar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
