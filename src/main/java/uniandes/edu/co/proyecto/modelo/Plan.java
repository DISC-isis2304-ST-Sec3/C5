package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="planes")

public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NOMBREPLAN")

    private String nombrePlan;
    @Column(name = "DESCUENTO")
    private double descuento;
    @Column(name = "PERIODOVIGENCIAINICIAL")
    private Date periodoVigenciaInicial;
    @Column(name = "PERIODOVIGENCIAFINAL")
    private Date periodoVigenciaFinal;
    
    public Plan(double descuento, Date periodoVigenciaInicial, Date periodoVigenciaFinal ){

        this.descuento=descuento;
        this.periodoVigenciaInicial=periodoVigenciaInicial;
        this.periodoVigenciaFinal=periodoVigenciaFinal;
    }
    
    public Plan(){;}

    public String getNombrePlan() {
        return nombrePlan;
    }

    public double getDescuento() {
        return descuento;
    }

    public Date getPeriodoVigenciaInicial() {
        return periodoVigenciaInicial;
    }

    public Date getPeriodoVigenciaFinal() {
        return periodoVigenciaFinal;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public void setPeriodoVigenciaInicial(Date periodoVigenciaInicial) {
        this.periodoVigenciaInicial = periodoVigenciaInicial;
    }

    public void setPeriodoVigenciaFinal(Date periodoVigenciaFinal) {
        this.periodoVigenciaFinal = periodoVigenciaFinal;
    }

    
}
