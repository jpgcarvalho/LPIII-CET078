package com.uesc;

public class PedidoCompra extends Compra {
    private int numPedido = 0;
    private Object[][] produtos;

    public PedidoCompra(int maximoCompras){
        super(maximoCompras);
        numPedido = 0;
        produtos = new Object[30][3];
    }

    public int precoCompra(int compraId) {
        int [][] dados = getDados();

        if(compraId >= 0 && compraId < getComprasIndice()){
            int valor = dados[compraId][4] * dados[compraId][5];
            return valor;
        }
        return -1;
    }

    public int totalCompras() {
        int [][] dados = getDados();
        int total = 0;

        for(int i = 0; i < getComprasIndice(); i++){
            total += dados[i][4] * dados[i][5];
        }
        if(total != 0) {
            return total;
        }

        return -1;
    }

    public int quantidadeEstoque(int codMaterial){
        for(int i = 0; i <= numPedido; i++){
            if((int)produtos[i][0] == codMaterial){
                return (int) produtos[i][2];
            }
        }
        return -1;
    }

    public void atualizarEstoque(int codigo, int quantidade){
        for(int i = 0; i < numPedido; i++){
            if((int)produtos[i][0] == codigo){
                produtos[i][2] = (int)produtos[i][2] - quantidade;
            }
        }
    }

    public void setProduto(int codigo, String produto, int estoque){
        produtos[numPedido][0] = codigo;
        produtos[numPedido][1] = produto;
        produtos[numPedido][2] = estoque;
        numPedido++;
    }


    public Object [][] getProdutos(){
        return produtos;
    }

    public int getIndiceProduto(){
        return numPedido;
    }
}
