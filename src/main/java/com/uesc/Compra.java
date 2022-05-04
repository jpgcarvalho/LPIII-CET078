package com.uesc;

public class Compra {
    private int material[][];
    private int compraIndice = 0;
    private int maximo;
    
    public Compra(int maximoCompras) {
        material = new int[maximoCompras][6];
        maximo = maximoCompras;
    }

    public boolean comprarProduto(int dia, int mes, int codMaterial, int quantidade, int preco){
        if(compraIndice == maximo){
            System.out.println("Não pode realizar mais nenhuma compra");
            return false;
        }else {
            material[compraIndice][0] = compraIndice;
            material[compraIndice][1] = dia;
            material[compraIndice][2] = mes;
            material[compraIndice][3] = codMaterial;
            material[compraIndice][4] = quantidade;
            material[compraIndice][5] = preco;
            compraIndice++;
            return true;
        }
    }

    public boolean excluirCompra(int idCompra){
        if (idCompra >= 0 && idCompra <= compraIndice) {
            for (int i = 0; i < compraIndice; i++) {
                if (material[i][0] == idCompra) {
                    material[i][0] = -1;
                    material[i][1] = 0;
                    material[i][2] = 0;
                    material[i][3] = -1;
                    material[i][4] = 0;
                    material[i][5] = 0;
                    return true;
                }
            }
        }
        return false;
        
    }

    public int[][] getDados() {
        return material;
    }

    public int getComprasIndice() {
        return compraIndice;
    }
}
