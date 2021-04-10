package vo;


import java.sql.Date;

public class CompraVo {
    private Integer codCliente;
    private Integer codArticulo;
    private Date fecha;
    private Integer unidades;


    public Integer getCodCliente() {return codCliente; }

    public void setCodCliente(Integer codCliente) {
        this.codCliente = codCliente;
    }

    public Integer getCodArticulo() { return codArticulo; }

    public void setCodArticulo (Integer codArticulo) {
        this.codArticulo = codArticulo;
    }

    public Date getFecha() {return fecha; }

  public void setFecha(Date fecha) { this.fecha = fecha; }


    public Integer getUnidades() {return unidades; }

    public void setUnidades(Integer unidades) {
        this.unidades = unidades;
    }
}