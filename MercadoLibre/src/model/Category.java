package model;

public enum Category {
    LIBROS(1, "libros"),
    ELECTRONICA(2, "electrónica"),
    ROPA_Y_ACCESORIOS(3, "ropa y accesorios"),
    ALIMENTOS_Y_BEBIDAS(4, "alimentos y bebidas"),
    PAPELERIA(5, "papeleria"),
    DEPORTES(6, "deportes"),
    BELLEZA_Y_CUIDADO_PERSONAL(7, "belleza y cuidado personal"),
    JUGUETES_Y_JUEGOS(8, "juguetes y juegos");

    private int num;
    private String name;

    Category(int num, String name) {
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