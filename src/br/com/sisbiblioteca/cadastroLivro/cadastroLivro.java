/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * cadastroLivro.java
 *
 * Created on 04/09/2012, 15:40:29
 */
package br.com.sisbiblioteca.cadastroLivro;

import br.com.sisbiblioteca.Sobre.Sobre;
import br.com.sisbiblioteca.cadastroEquipamento.cadastroequipamento;
import br.com.sisbiblioteca.cadastroInstituicao.cadastroInstituicao;
import br.com.sisbiblioteca.cadastrolocatario.cadastrolocatario;
import br.com.sisbiblioteca.cadastrousuarios.cadastrousuario;
import br.com.sisbiblioteca.devolucaoEquip.DevolucaoEquip;
import br.com.sisbiblioteca.devolucaolivros.devolucaolivro;
import br.com.sisbiblioteca.emprestimoEquipamento.EmprestimoEquipamento;
import br.com.sisbiblioteca.emprestimoLivro.registroLivro;
import br.com.sisbiblioteca.login.login;
import br.com.sisbiblioteca.pendenciasEquip.pendenciasEquip;
import br.com.sisbiblioteca.principal.principal;
import br.com.sisbiblioteca.registroequipamentos.reservaequipamento;
import br.com.sisbiblioteca.reservaLivro.reservaLivro;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Murilo Alves
 */
public class cadastroLivro extends javax.swing.JFrame {

    /**
     * Creates new form cadastroLivro
     */
    public cadastroLivro() {
        initComponents();
        setExtendedState(MAXIMIZED_BOTH);
        camposNaoEditaveis();
        msg();
    }
    public static String nome;

    public void msg() {
        msgNome.setText("" + nome);

        java.util.Date h = new java.util.Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        lbDate.setText("" + formato.format(h));

        iniciaRelogio();

    }

    public void iniciaRelogio() {
        new Thread() {//instancia nova thread já implementando o método run()
            public void run() {//sobrescreve o método run()
                while (0 == 0) {//while para fazer o loop infinito
                    GregorianCalendar gc = new GregorianCalendar();//novo gregorian calendar, onde temos a data atual
                    int hora = gc.get(Calendar.HOUR_OF_DAY);//pega as horas
                    int minuto = gc.get(Calendar.MINUTE);//pega os minutos
                    int segundo = gc.get(Calendar.SECOND);//pega os segundos
                    String horaString;//nova string horas
                    String minString;//nova string minutos
                    String segundoString;//nova string segundos
                    if (hora < 10) {//se hora for menor que 10 precisa colocar um 0 à esquerda
                        horaString = "0" + hora;
                    } else {
                        horaString = "" + hora;
                    }
                    if (minuto < 10) {//se minuto for menor que 10 precisa colocar um 0 à esquerda
                        minString = "0" + minuto;
                    } else {
                        minString = "" + minuto;
                    }
                    if (segundo < 10) {//se segundo for menor que 10 precisa colocar um 0 à esquerda
                        segundoString = "0" + segundo;
                    } else {
                        segundoString = "" + segundo;
                    }
                    lbHora.setText("" + horaString + ":" + minString + ":" + segundoString);//seta hora atual no label
                    try {
                        sleep(1000);//faz a thread entrar em estado de espera por 1000 milissegundos ou 1 segundo
                    } catch (Exception e) {
                    }
                }
            }
        }.start();//inicia a thread.
    }
    String escolha;
    //Cadastro

    private void cadastroLivro() {

        livroBean ab = new livroBean();
        ab.setNomelivro(tfNome.getText().trim());
        ab.setAutor(tfAutor.getText().trim());
        ab.setCategoria(tfCategoria.getText().trim());
        ab.setAnopublicacao(Integer.parseInt(String.valueOf(tfAnoPublicacao.getText().trim())));
        ab.setEditora(tfEditora.getText().trim());
        ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText().trim())));
        ab.setEdicao(Integer.parseInt(String.valueOf(tfEdicao.getText().trim())));
        ab.setGenero(tfGenero.getText().trim());
        ab.setClassificacaoEtaria(Integer.parseInt(String.valueOf(tfClassificacaoetaria.getText().trim())));
        ab.setVolume(Integer.parseInt(String.valueOf(tfVolume.getText().trim())));
        ab.setStatus("disponivel");

        if (tfNome.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");

        } else if (tfAutor.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Autor do Livro!!!");
        } else if (tfEdicao.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Edição!!!");

        } else if (tfEditora.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Editora!!!");
        } else if (tfQuantidade.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Quantidade!!");

        } else if (tfCategoria.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Categoria!!!");

        } else if (tfAnoPublicacao.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Publicação!!!");
        } else if (tfGenero.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Gênero!!!");

        } else if (tfVolume.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Volume!!!");

        } else {
            int quantidade = Integer.parseInt(String.valueOf(tfQuantidade.getText().trim()));

            for (int i = 0; i < quantidade; i++) {

                livroControl ce = new livroControl();
                ce.CadastroLivro(ab);



                btSalvar.setEnabled(false);
                limparCampos();
                camposNaoEditaveis();
                listarLivro();
            }
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso !!!");
        }
    }
    //Listar Livros
    List<livroBean> livroBean;
    DefaultTableModel tmLivros = new DefaultTableModel(null, new String[]{"Código", "Nome", "Autor", "Categoria", "Gênero", "Editora", "Clas.Etária"});
    ListSelectionModel lsmLivros;

    public void mostrarLivros(List<livroBean> livrosB) {
        while (tmLivros.getRowCount() > 0) {
            tmLivros.removeRow(0);
        }
        if (livrosB.size() == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum Livro encontrado!");
        } else {
            String[] campos = new String[]{null, null, null, null, null, null};
            for (int i = 0; i < livrosB.size(); i++) {
                tmLivros.addRow(campos);
                tmLivros.setValueAt(livrosB.get(i).getIdlivro(), i, 0);
                tmLivros.setValueAt(livrosB.get(i).getNomelivro(), i, 1);
                tmLivros.setValueAt(livrosB.get(i).getAutor(), i, 2);
                tmLivros.setValueAt(livrosB.get(i).getCategoria(), i, 3);
                tmLivros.setValueAt(livrosB.get(i).getGenero(), i, 4);
                tmLivros.setValueAt(livrosB.get(i).getEditora(), i, 5);
                tmLivros.setValueAt(livrosB.get(i).getClassificacaoEtaria(), i, 6);

            }
        }
    }
    //Listar Por Nome

    public void listarLivro() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivros("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Autor

    public void listarLivroAutor() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosAutor("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }

    //Listar Por Categoria
    public void listarLivroCategoria() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosCategoria("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Genero

    public void listarLivroGenero() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivrosGenero("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }
    //Listar Por Editora

    public void listarLivroEditora() {
        livroControl ce = new livroControl();
        livroBean = ce.ListarLivros("%" + tfPesquisar.getText().trim() + "%");

        mostrarLivros(livroBean);

    }

    //Listar
    private void opcaoLivro() {

        if (cbLivro.getSelectedItem().equals("...")) {
            JOptionPane.showMessageDialog(null, "Selecione um tipo de livro primeiro!!", "Erro", JOptionPane.ERROR_MESSAGE);
        } else if (cbLivro.getSelectedItem().equals("Nome")) {
            listarLivro();
        } else if (cbLivro.getSelectedItem().equals("Autor")) {
            listarLivroAutor();
        } else if (cbLivro.getSelectedItem().equals("Categoria")) {
            listarLivroCategoria();
        } else if (cbLivro.getSelectedItem().equals("Gênero")) {
            listarLivroGenero();
        } else if (cbLivro.getSelectedItem().equals("Editora")) {
            listarLivroEditora();
        }
    }
    //Alterar Livro

    public boolean verificaCampos() {
        if (tabelaLivros.getSelectedRow() != -1) {
            return true;
        }
        return false;
    }
    public static String nomeLivro;
    public static String nomeAutor;
    public static String categoria;
    public static int anopublicacao;
    public static int volume;

    private void alterarLivro() {
        if (verificaCampos()) {
            livroBean ab = new livroBean();
            ab.setIdlivro(livroBean.get(tabelaLivros.getSelectedRow()).getIdlivro());
            ab.setNomelivro(tfNome.getText().trim());

            ab.setAutor(tfAutor.getText().trim());

            ab.setCategoria(tfCategoria.getText().trim());

            ab.setAnopublicacao(Integer.parseInt(String.valueOf(tfAnoPublicacao.getText().trim())));

            ab.setEditora(tfEditora.getText().trim());
            ab.setQuantidade(Integer.parseInt(String.valueOf(tfQuantidade.getText().trim())));
            ab.setEdicao(Integer.parseInt(String.valueOf(tfEdicao.getText().trim())));
            ab.setGenero(tfGenero.getText().trim());
            ab.setClassificacaoEtaria(Integer.parseInt(String.valueOf(tfClassificacaoetaria.getText().trim())));
            ab.setVolume(Integer.parseInt(String.valueOf(tfVolume.getText().trim())));


            if (tfNome.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");
            } else if (tfAutor.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Autor do Livro!!!");
            } else if (tfEdicao.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Edição!!!");

            } else if (tfEditora.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Editora!!!");
            } else if (tfQuantidade.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Quantidade!!");

            } else if (tfCategoria.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Categoria!!!");

            } else if (tfAnoPublicacao.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Publicação!!!");

            } else if (tfGenero.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Gênero!!!");

            } else if (tfVolume.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Volume!!!");

            } else if (tfClassificacaoetaria.getText().equals("")) {

                JOptionPane.showMessageDialog(null, "Preencha o campo Classificacao etária!!!");



            } else {
                livroControl ac = new livroControl();
                ac.alterarLivro(ab);

                JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso !!!");

                btSalvar.setEnabled(false);
                limparCampos();
                camposNaoEditaveis();
                listarLivro();
            }
        }
    }

    private void linhaTabela() {
        if (tabelaLivros.getSelectedRow() != -1) {
            camposEditaveis();
            btSalvar.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(null, "Selecione o Livro! ", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void TabelaLinhaSelecionadaLivros(JTable tb) {
        if (tabelaLivros.getSelectedRow() != -1) {
            tfNome.setText(livroBean.get(tb.getSelectedRow()).getNomelivro());
            tfAutor.setText(livroBean.get(tb.getSelectedRow()).getAutor());
            tfCategoria.setText(livroBean.get(tb.getSelectedRow()).getCategoria());
            tfAnoPublicacao.setText(livroBean.get(tb.getSelectedRow()).getAnopublicacao().toString());
            tfEditora.setText(livroBean.get(tb.getSelectedRow()).getEditora());
            tfQuantidade.setText(livroBean.get(tb.getSelectedRow()).getQuantidade().toString());
            tfEdicao.setText(livroBean.get(tb.getSelectedRow()).getEdicao().toString());
            tfGenero.setText(livroBean.get(tb.getSelectedRow()).getGenero());
            tfVolume.setText(livroBean.get(tb.getSelectedRow()).getVolume().toString());
            tfClassificacaoetaria.setText(livroBean.get(tb.getSelectedRow()).getVolume().toString());

            nomeLivro = livroBean.get(tabelaLivros.getSelectedRow()).getNomelivro();
            nomeAutor = livroBean.get(tabelaLivros.getSelectedRow()).getAutor();
            categoria = livroBean.get(tabelaLivros.getSelectedRow()).getCategoria();
            anopublicacao = Integer.parseInt(String.valueOf(livroBean.get(tabelaLivros.getSelectedRow()).getAnopublicacao()));
            volume = Integer.parseInt(String.valueOf(livroBean.get(tabelaLivros.getSelectedRow()).getVolume().toString()));

        } else {
            limparCampos();
        }
    }

//Limpar Campos
    private void camposEditaveis() {
        tfNome.setEditable(true);
        tfAutor.setEditable(true);
        tfCategoria.setEditable(true);
        tfAnoPublicacao.setEditable(true);
        tfEditora.setEditable(true);
        tfQuantidade.setEditable(true);
        tfEdicao.setEditable(true);
        tfGenero.setEditable(true);
        tfClassificacaoetaria.setEditable(true);
        tfVolume.setEditable(true);
    }

    private void camposNaoEditaveis() {
        tfNome.setEditable(false);
        tfAutor.setEditable(false);
        tfCategoria.setEditable(false);
        tfAnoPublicacao.setEditable(false);
        tfEditora.setEditable(false);
        tfQuantidade.setEditable(false);
        tfEdicao.setEditable(false);
        tfVolume.setEditable(false);
        tfGenero.setEditable(false);
        tfClassificacaoetaria.setEditable(false);
        tfVolume.setEditable(false);
    }

    private void limparCampos() {

        tfNome.setText("");
        tfAutor.setText("");
        tfCategoria.setText("");
        tfAnoPublicacao.setText("");
        tfEditora.setText("");
        tfQuantidade.setText("");
        tfEdicao.setText("");
        tfGenero.setText("");
        tfClassificacaoetaria.setText("");
        tfVolume.setText("");


    }

    public void vcampos() {
        if (tfNome.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");

        } else if (tfClassificacaoetaria.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Classificacao!!!");
        } else if (tfVolume.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Volume!!!");
        } else if (tfGenero.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo gênero!!!");

        } else if (tfAutor.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Autor do Livro!!!");
        } else if (tfEdicao.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Edição!!!");

        } else if (tfEditora.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Editora!!!");
        } else if (tfQuantidade.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Quantidade!!");

        } else if (tfCategoria.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Categoria!!!");

        } else if (tfAnoPublicacao.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Publicação!!!");

        } else {
            cadastroLivro();
        }
    }

    public void alter() {
        if (tfNome.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Nome !!!");

        } else if (tfClassificacaoetaria.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Classificacao!!!");
        } else if (tfVolume.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Volume!!!");
        } else if (tfGenero.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo gênero!!!");

        } else if (tfAutor.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Autor do Livro!!!");
        } else if (tfEdicao.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Edição!!!");

        } else if (tfEditora.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Editora!!!");
        } else if (tfQuantidade.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Quantidade!!");

        } else if (tfCategoria.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Categoria!!!");

        } else if (tfAnoPublicacao.getText().equals("")) {

            JOptionPane.showMessageDialog(null, "Preencha o campo Publicação!!!");

        } else {
            alterarLivro();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfAutor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfQuantidade = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfEditora = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfAnoPublicacao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tfCategoria = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfVolume = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfClassificacaoetaria = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        tfGenero = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        tfEdicao = new javax.swing.JTextField();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        btNovo = new javax.swing.JButton();
        btAlterar = new javax.swing.JButton();
        cbLivro = new javax.swing.JComboBox();
        tfPesquisar = new javax.swing.JTextField();
        btPesquisar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaLivros = new javax.swing.JTable();
        tfNome = new javax.swing.JTextField();
        mnuPrincipal = new javax.swing.JButton();
        Ramiro = new javax.swing.JPanel();
        lbDate = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        msgNome = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lbHora = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuOpcoes = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        nmuInstituicao = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        mnuPrincipal1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mnuSair = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mnuLocatarios = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mnuLivros = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentos = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        mnuReservas = new javax.swing.JMenu();
        mnuLivrosR = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentosE = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenu5 = new javax.swing.JMenu();
        mnuLivroE = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentoE = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenu6 = new javax.swing.JMenu();
        mnuLivroD = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        mnuEquipamentoD = new javax.swing.JMenuItem();
        Pendencias = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        mnuSobre = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Bibliotecário - Cadastro de Livros");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/imagens/icone.png")));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cadastro de Livro"));

        jLabel1.setText("Nome do Livro:");

        jLabel2.setText("Autor:");

        jLabel3.setText("Quantidade:");

        tfQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfQuantidadeActionPerformed(evt);
            }
        });

        jLabel4.setText("Editora:");

        jLabel5.setText("Ano Publicação:");

        jLabel6.setText("Categoria:");

        jLabel7.setText("Volume:");

        jLabel8.setText("Clas. Etária:");

        jLabel9.setText("Gênero:");

        jLabel10.setText("Edição:");

        btSalvar.setText("Salvar");
        btSalvar.setEnabled(false);
        btSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalvarActionPerformed(evt);
            }
        });

        btExcluir.setText("Excluir");
        btExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirActionPerformed(evt);
            }
        });

        btNovo.setText("Novo");
        btNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoActionPerformed(evt);
            }
        });

        btAlterar.setText("Alterar");
        btAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAlterarActionPerformed(evt);
            }
        });

        cbLivro.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"...", "Nome", "Autor", "Categoria", "Gênero", "Editora"}));

        tfPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPesquisarActionPerformed(evt);
            }
        });

        btPesquisar.setText("Pesquisar");
        btPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarActionPerformed(evt);
            }
        });

        tabelaLivros.setModel(tmLivros);
        tabelaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsmLivros = tabelaLivros.getSelectionModel();
        lsmLivros.addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e) {
                if(! e.getValueIsAdjusting()) {
                    TabelaLinhaSelecionadaLivros(tabelaLivros);
                }
            }
        });
        jScrollPane2.setViewportView(tabelaLivros);

        mnuPrincipal.setText("Menu Principal");
        mnuPrincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPrincipalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cbLivro, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(tfClassificacaoetaria, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfGenero))
                                    .addComponent(tfCategoria, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfEditora)
                                    .addComponent(tfAutor))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(tfVolume, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                                .addComponent(tfAnoPublicacao, javax.swing.GroupLayout.Alignment.TRAILING))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(tfNome))))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnuPrincipal))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAlterar, btExcluir, btNovo, btSalvar, mnuPrincipal});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tfEdicao, tfVolume});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btNovo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(mnuPrincipal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(tfNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(tfAnoPublicacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(tfVolume, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tfCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tfClassificacaoetaria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel9)
                                .addComponent(tfGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(tfEdicao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAlterar, btExcluir, btNovo, btSalvar, mnuPrincipal});

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.ipady = 21;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(19, 4, 0, 0);
        getContentPane().add(jPanel1, gridBagConstraints);

        Ramiro.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        Ramiro.setLayout(null);
        Ramiro.add(lbDate);
        lbDate.setBounds(10, 4, 100, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 141;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 1, 10);
        getContentPane().add(Ramiro, gridBagConstraints);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setLayout(null);

        jLabel11.setText("Usuário:");
        jPanel5.add(jLabel11);
        jLabel11.setBounds(10, 0, 60, 30);
        jPanel5.add(msgNome);
        msgNome.setBounds(70, 0, 360, 30);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 439;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 6, 1, 0);
        getContentPane().add(jPanel5, gridBagConstraints);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel4.setLayout(null);
        jPanel4.add(lbHora);
        lbHora.setBounds(30, 4, 90, 20);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 119;
        gridBagConstraints.ipady = 29;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(11, 4, 1, 0);
        getContentPane().add(jPanel4, gridBagConstraints);

        mnuOpcoes.setText("Opções");
        mnuOpcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuOpcoesActionPerformed(evt);
            }
        });

        jMenu4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/conf.png"))); // NOI18N
        jMenu4.setText("Configurações");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        nmuInstituicao.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nmuInstituicao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/School.png"))); // NOI18N
        nmuInstituicao.setText("Instituição/Escola");
        nmuInstituicao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nmuInstituicaoActionPerformed(evt);
            }
        });
        jMenu4.add(nmuInstituicao);

        mnuOpcoes.add(jMenu4);
        mnuOpcoes.add(jSeparator11);

        mnuPrincipal1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuPrincipal1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/home_blue.png"))); // NOI18N
        mnuPrincipal1.setText("Menu Principal");
        mnuPrincipal1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuPrincipal1ActionPerformed(evt);
            }
        });
        mnuOpcoes.add(mnuPrincipal1);
        mnuOpcoes.add(jSeparator1);

        mnuSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/k-gpg-icon.png"))); // NOI18N
        mnuSair.setText("Sair");
        mnuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSairActionPerformed(evt);
            }
        });
        mnuOpcoes.add(mnuSair);

        jMenuBar1.add(mnuOpcoes);

        jMenu1.setText("Cadastros");

        mnuLocatarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mnuLocatarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/Users-icon.png"))); // NOI18N
        mnuLocatarios.setText("Locatários");
        mnuLocatarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLocatariosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuLocatarios);
        jMenu1.add(jSeparator5);

        mnuLivros.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mnuLivros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivros.setText("Livros");
        mnuLivros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivrosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuLivros);
        jMenu1.add(jSeparator6);

        mnuEquipamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentos.setText("Equipamentos");
        mnuEquipamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentosActionPerformed(evt);
            }
        });
        jMenu1.add(mnuEquipamentos);
        jMenu1.add(jSeparator7);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/usuario.png"))); // NOI18N
        jMenuItem1.setText("Usuários");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Registrar");

        mnuReservas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/reservar.png"))); // NOI18N
        mnuReservas.setText("Reservas");

        mnuLivrosR.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        mnuLivrosR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivrosR.setText("Livros");
        mnuLivrosR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivrosRActionPerformed(evt);
            }
        });
        mnuReservas.add(mnuLivrosR);
        mnuReservas.add(jSeparator8);

        mnuEquipamentosE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentosE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentosE.setText("Equipamentos");
        mnuEquipamentosE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentosEActionPerformed(evt);
            }
        });
        mnuReservas.add(mnuEquipamentosE);

        jMenu3.add(mnuReservas);
        jMenu3.add(jSeparator3);

        jMenu5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/save_go.png"))); // NOI18N
        jMenu5.setText("Emprestimos");

        mnuLivroE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuLivroE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivroE.setText("Livros");
        mnuLivroE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivroEActionPerformed(evt);
            }
        });
        jMenu5.add(mnuLivroE);
        jMenu5.add(jSeparator9);

        mnuEquipamentoE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentoE.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentoE.setText("Equipamentos");
        mnuEquipamentoE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentoEActionPerformed(evt);
            }
        });
        jMenu5.add(mnuEquipamentoE);

        jMenu3.add(jMenu5);
        jMenu3.add(jSeparator4);

        jMenu6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/return.png"))); // NOI18N
        jMenu6.setText("Devolução");

        mnuLivroD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.SHIFT_MASK));
        mnuLivroD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        mnuLivroD.setText("Livros");
        mnuLivroD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuLivroDActionPerformed(evt);
            }
        });
        jMenu6.add(mnuLivroD);
        jMenu6.add(jSeparator10);

        mnuEquipamentoD.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnuEquipamentoD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        mnuEquipamentoD.setText("Equipamentos");
        mnuEquipamentoD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuEquipamentoDActionPerformed(evt);
            }
        });
        jMenu6.add(mnuEquipamentoD);

        jMenu3.add(jMenu6);

        jMenuBar1.add(jMenu3);

        Pendencias.setText("Pendências");
        Pendencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PendenciasActionPerformed(evt);
            }
        });

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/vaio.png"))); // NOI18N
        jMenuItem2.setText("Equipamentos");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        Pendencias.add(jMenuItem2);
        Pendencias.add(jSeparator2);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/bookcase.png"))); // NOI18N
        jMenuItem4.setText("Livros");
        Pendencias.add(jMenuItem4);

        jMenuBar1.add(Pendencias);

        mnuSobre.setText("Sobre");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/sobre.png"))); // NOI18N
        jMenuItem3.setText("Sobre o Sistema");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        mnuSobre.add(jMenuItem3);

        jMenuBar1.add(mnuSobre);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-744)/2, (screenSize.height-524)/2, 744, 524);
    }// </editor-fold>//GEN-END:initComponents

private void tfQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfQuantidadeActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_tfQuantidadeActionPerformed

private void btSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalvarActionPerformed
    if (escolha == "Novo") {
        vcampos();
    } else if (escolha == "Alterar") {
        alter();
    }

}//GEN-LAST:event_btSalvarActionPerformed

private void btPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarActionPerformed
    opcaoLivro();
}//GEN-LAST:event_btPesquisarActionPerformed

private void btExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirActionPerformed
    if (tabelaLivros.getSelectedRow() != -1) {
        int resposta = JOptionPane.showConfirmDialog(this, "Atenção!!\nVocê certeza que quer excluir todos os livros\n\"Sim\" para continuar", "Verificação!!", JOptionPane.WARNING_MESSAGE);
        if (resposta == JOptionPane.YES_OPTION) {
            livroControl c = new livroControl();
            c.excluirLivro();
            tabelaLivros.clearSelection();
            opcaoLivro();
        }
    } else {
        JOptionPane.showMessageDialog(null, "Selecione um Livro!");
    }

}//GEN-LAST:event_btExcluirActionPerformed

private void btAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAlterarActionPerformed
    linhaTabela();
    escolha = "Alterar";
    btSalvar.setEnabled(true);
}//GEN-LAST:event_btAlterarActionPerformed

private void btNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoActionPerformed
    btSalvar.setEnabled(true);
    escolha = "Novo";
    camposEditaveis();
}//GEN-LAST:event_btNovoActionPerformed

private void mnuPrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrincipalActionPerformed
    principal p = new principal();
    p.setVisible(true);
    dispose();
}//GEN-LAST:event_mnuPrincipalActionPerformed

    private void tfPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPesquisarActionPerformed
        opcaoLivro();
    }//GEN-LAST:event_tfPesquisarActionPerformed

    private void nmuInstituicaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nmuInstituicaoActionPerformed
        cadastroInstituicao ci = new cadastroInstituicao();
        ci.setVisible(true);
    }//GEN-LAST:event_nmuInstituicaoActionPerformed

    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed

    }//GEN-LAST:event_jMenu4ActionPerformed

    private void mnuPrincipal1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuPrincipal1ActionPerformed
        principal p = new principal();
        p.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuPrincipal1ActionPerformed

    private void mnuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSairActionPerformed
        int resposta = JOptionPane.showConfirmDialog(this, "Ao sair desta tela verifique se todos os dados foram salvos.\n Deseja realmente sair?","Verificação de Saída!!!", JOptionPane.YES_OPTION);
        if (resposta == JOptionPane.YES_OPTION) {
            login l = new login();
            l.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_mnuSairActionPerformed

    private void mnuOpcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuOpcoesActionPerformed

    }//GEN-LAST:event_mnuOpcoesActionPerformed

    private void mnuLocatariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLocatariosActionPerformed
        cadastrolocatario cl = new cadastrolocatario();
        cl.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuLocatariosActionPerformed

    private void mnuLivrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivrosActionPerformed
        cadastroLivro clv = new cadastroLivro();
        clv.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuLivrosActionPerformed

    private void mnuEquipamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentosActionPerformed
        cadastroequipamento ce = new cadastroequipamento();
        ce.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuEquipamentosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        cadastrousuario cus = new cadastrousuario();
        cus.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void mnuLivrosRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivrosRActionPerformed
        reservaLivro rl = new reservaLivro();
        rl.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuLivrosRActionPerformed

    private void mnuEquipamentosEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentosEActionPerformed
        reservaequipamento re = new reservaequipamento();
        re.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuEquipamentosEActionPerformed

    private void mnuLivroEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivroEActionPerformed
        registroLivro rl = new registroLivro();
        rl.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuLivroEActionPerformed

    private void mnuEquipamentoEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentoEActionPerformed
        EmprestimoEquipamento e = new EmprestimoEquipamento();
        e.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuEquipamentoEActionPerformed

    private void mnuLivroDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuLivroDActionPerformed
        devolucaolivro rl = new devolucaolivro();
        rl.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuLivroDActionPerformed

    private void mnuEquipamentoDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuEquipamentoDActionPerformed
        DevolucaoEquip d = new DevolucaoEquip();
        d.setVisible(true);
        dispose();
    }//GEN-LAST:event_mnuEquipamentoDActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        pendenciasEquip pe = new pendenciasEquip();
        pe.setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void PendenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PendenciasActionPerformed

    }//GEN-LAST:event_PendenciasActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        Sobre s = new Sobre();
        s.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

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
            java.util.logging.Logger.getLogger(cadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cadastroLivro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cadastroLivro().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Pendencias;
    private javax.swing.JPanel Ramiro;
    private javax.swing.JButton btAlterar;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btNovo;
    private javax.swing.JButton btPesquisar;
    private javax.swing.JButton btSalvar;
    private javax.swing.JComboBox cbLivro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JLabel lbDate;
    private javax.swing.JLabel lbHora;
    private javax.swing.JMenuItem mnuEquipamentoD;
    private javax.swing.JMenuItem mnuEquipamentoE;
    private javax.swing.JMenuItem mnuEquipamentos;
    private javax.swing.JMenuItem mnuEquipamentosE;
    private javax.swing.JMenuItem mnuLivroD;
    private javax.swing.JMenuItem mnuLivroE;
    private javax.swing.JMenuItem mnuLivros;
    private javax.swing.JMenuItem mnuLivrosR;
    private javax.swing.JMenuItem mnuLocatarios;
    private javax.swing.JMenu mnuOpcoes;
    private javax.swing.JButton mnuPrincipal;
    private javax.swing.JMenuItem mnuPrincipal1;
    private javax.swing.JMenu mnuReservas;
    private javax.swing.JMenuItem mnuSair;
    private javax.swing.JMenu mnuSobre;
    private javax.swing.JLabel msgNome;
    private javax.swing.JMenuItem nmuInstituicao;
    private javax.swing.JTable tabelaLivros;
    private javax.swing.JTextField tfAnoPublicacao;
    private javax.swing.JTextField tfAutor;
    private javax.swing.JTextField tfCategoria;
    private javax.swing.JTextField tfClassificacaoetaria;
    private javax.swing.JTextField tfEdicao;
    private javax.swing.JTextField tfEditora;
    private javax.swing.JTextField tfGenero;
    private javax.swing.JTextField tfNome;
    private javax.swing.JTextField tfPesquisar;
    private javax.swing.JTextField tfQuantidade;
    private javax.swing.JTextField tfVolume;
    // End of variables declaration//GEN-END:variables
}
