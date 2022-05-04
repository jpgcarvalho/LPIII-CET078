package com.uesc;

import javax.swing.*;

public class Tela {
    PedidoCompra pedidos;
    JFrame frame = new JFrame();

    public Tela() {
        Inicialize();
    }

    public void Screen(String title){
        frame.setTitle(title);
        frame.setSize(600, 500);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void Inicialize(){
        int maximo = Integer.parseInt(JOptionPane.showInputDialog("Qual a quantidade máxima de compras: "));
        if(maximo > 0){
            pedidos = new PedidoCompra(maximo);
            CadastrarProd();
        }
    }

    public void CadastrarProd() {
        JButton cadastroButton = new JButton("Cadastrar Produto");
        cadastroButton.setBounds(20, 20, 150, 40);
        frame.add(cadastroButton);

        JButton exibirProdButton = new JButton("Exibir Produtos");
        exibirProdButton.setBounds(20, 70, 150, 40);
        frame.add(exibirProdButton);

        JButton comprarButton = new JButton("Realizar Compra");
        comprarButton.setBounds(20, 120, 150, 40);
        frame.add(comprarButton);

        JButton excluirCompraButton = new JButton("Excluir Compra");
        excluirCompraButton.setBounds(200, 20, 150, 40);
        frame.add(excluirCompraButton);

        JButton precoCompraButton = new JButton("Preço da Compra");
        precoCompraButton.setBounds(200, 70, 150, 40);
        frame.add(precoCompraButton);
        
        JButton precoTotalComprasButtons = new JButton("Preço total das Contas");
        precoTotalComprasButtons.setBounds(200, 120, 150, 40);
        frame.add(precoTotalComprasButtons);


        cadastroButton.addActionListener(e-> {
            int codigo, estoque;
            String produto;
            codigo = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do produto"));
            produto = JOptionPane.showInputDialog("Digite o nome do produto");
            estoque = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade do produto"));

            pedidos.setProduto(codigo, produto, estoque);

            JOptionPane.showMessageDialog(null, "Produto Cadastrado!");
        });

        exibirProdButton.addActionListener(e-> {
            JFrame ScreenProduct = new JFrame("Produtos Cadastrados");
            Object[][] data = pedidos.getProdutos();

            JLabel codigo = new JLabel("Código");
            JLabel produto = new JLabel("Produto");
            JLabel estoque = new JLabel("Estoque");

            codigo.setBounds(40, 20, 130, 20);
            codigo.setHorizontalAlignment(JLabel.CENTER);
            produto.setBounds(200, 20, 130, 20);
            produto.setHorizontalAlignment(JLabel.CENTER);
            estoque.setBounds(360, 20, 130, 20);
            estoque.setHorizontalAlignment(JLabel.CENTER);

            JLabel[] cod = new JLabel[pedidos.getIndiceProduto()+1];
            JLabel[] prod = new JLabel[pedidos.getIndiceProduto()+1];
            JLabel[] est = new JLabel[pedidos.getIndiceProduto()+1];
            for (int i = 0; i < pedidos.getIndiceProduto(); i++){
                cod[i] = new JLabel(Integer.toString((int)data[i][0]));
                prod[i] = new JLabel((String)data[i][1]);
                est[i] = new JLabel(Integer.toString((int)data[i][2]));
                
                cod[i].setBounds(40, 20+30+(30*i), 150, 30);
                cod[i].setHorizontalAlignment(JLabel.CENTER);
                prod[i].setBounds(200, 20+30+(30*i), 150, 30);
                prod[i].setHorizontalAlignment(JLabel.CENTER);
                est[i].setBounds(360, 20+30+(30*i), 150, 30);
                est[i].setHorizontalAlignment(JLabel.CENTER);
                
                ScreenProduct.add(cod[i]);
                ScreenProduct.add(prod[i]);
                ScreenProduct.add(est[i]);
            }
            
            ScreenProduct.add(codigo);
            ScreenProduct.add(produto);
            ScreenProduct.add(estoque);
            ScreenProduct.setSize(600, 500);
            ScreenProduct.setLayout(null);
            ScreenProduct.setLocationRelativeTo(null);
            ScreenProduct.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            ScreenProduct.setVisible(true);
        });

        comprarButton.addActionListener(e-> {
            int dia = Integer.parseInt(JOptionPane.showInputDialog("Insira o dia da compra: "));
            int mes = Integer.parseInt(JOptionPane.showInputDialog("Insira o mês da compra: "));
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Insira o Código do Produto da compra: "));
            int quantidade = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade do produto: "));
            int preco = Integer.parseInt(JOptionPane.showInputDialog("Insira o preço do produto: "));
            
            if (preco <= 0) {
                JOptionPane.showMessageDialog(null, "Preço inválido.");
            }
            
            else if (pedidos.quantidadeEstoque(codigo) < quantidade) {
                if (pedidos.quantidadeEstoque(codigo) == -1) {
                    JOptionPane.showMessageDialog(null, "Código de Produto não encontrado.");
                }
                JOptionPane.showMessageDialog(null, "Não há estoque suficiente para realizar a compra.");
            }
            
            else {
                if (pedidos.comprarProduto(dia, mes, codigo, quantidade, preco)) {
                    pedidos.atualizarEstoque(codigo, quantidade);
                    JOptionPane.showMessageDialog(null, "Compra realizada com sucesso.");
                }
            
                else {
                    JOptionPane.showMessageDialog(null, "Limite de Compras Atingido.");
                }
            }
        });

        excluirCompraButton.addActionListener(e-> {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código da conta a ser excluida: "));

            if(pedidos.excluirCompra(codigo)){
                JOptionPane.showMessageDialog(null, "Compra exlcluída!");
            }
            else{
                JOptionPane.showMessageDialog(null, "Houve um Erro ao excluir a compra");
            }
        });

        precoCompraButton.addActionListener(e-> {
            int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código da conta a ser excluida: "));
            int preco = pedidos.precoCompra(codigo);

            if(preco != -1){
                JOptionPane.showMessageDialog(null, "Compra " + codigo + ": " + "R$" + preco + ",00. ");
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Compra não encontrada!");
            }
        });

        precoTotalComprasButtons.addActionListener(e-> {
            int total = pedidos.totalCompras();

            if(total != -1){
                JOptionPane.showMessageDialog(null, "O preço total das compras: R$" + total + ",00 .");
            }
            else{
                JOptionPane.showMessageDialog(null, "Erro ao calcular total das compras");

            }
        });

        Screen("Gerenciador de compras");
    }
}
