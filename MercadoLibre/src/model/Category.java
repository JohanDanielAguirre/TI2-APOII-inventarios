package model;

public enum Category {
    LIBROS(1),
    ELECTRONICA(2),
    ROPA_Y_ACCESORIOS(3),
    ALIMENTOS_Y_BEBIDAS(4),
    PAPELERIA(5),
    DEPORTES(6),
    BELLEZA_Y_CUIDADO_PERSONAL(7),
    JUGUETES_Y_JUEGOS(8);

    private int num;
    private String name;

    Category(int num) {
        this.num = num;
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}