import java.util.ArrayList;

public class Empresa {
    ArrayList<Empleado> empleados;
    

    public static ArrayList<Double> liquidarNominaEmp(ArrayList<Empleado> empleados) {

        ArrayList<Double> valueToRerun = new ArrayList<Double>();
        for (int i = 0; i < empleados.size(); i++) {
            valueToRerun.add(calculosNominaEmp(empleados, i));
        }
        return valueToRerun;
    }

    public static ArrayList<Double> liquidarParafiscales(ArrayList<Empleado> empleados) {

        ArrayList<Double> valueToRerun = new ArrayList<Double>();
        for (int i = 0; i < empleados.size(); i++) {
            valueToRerun.add(calculosParafiscales(empleados, i));
        }
        return valueToRerun;
    }

    public static ArrayList<Double> liquidarSegSocialEmp(ArrayList<Empleado> empleados) {
        ArrayList<Double> valueToRerun = new ArrayList<Double>();
        for (int i = 0; i < empleados.size(); i++) {
            valueToRerun.add(calculosSegSocial(empleados, i));
        }
        return valueToRerun;
    }

    public static ArrayList<Double> liquidarPrestacionesEmp(ArrayList<Empleado> empleados) {
        ArrayList<Double> valueToRerun = new ArrayList<Double>();
        for (int i = 0; i < empleados.size(); i++) {
            valueToRerun.add(calculosPrestaciones(empleados, i));
        }
        return valueToRerun;
    }

    public static ArrayList<Double> totalPagosOtorgadosAEmpleados(ArrayList<Empleado> empleados) {
        ArrayList<Double> valueToRerun = new ArrayList<Double>();
        for (int i = 0; i < empleados.size(); i++) {
            valueToRerun.add(calculosPagosTotales(empleados, i));
        }
        return valueToRerun;
    }

    private static double calculosPagosTotales(ArrayList<Empleado> empleados, int i){
    double pagoTotal = 0;
    ArrayList<Double> prestaciones = liquidarPrestacionesEmp(empleados);
    ArrayList<Double> nomina = liquidarNominaEmp(empleados);
    pagoTotal = prestaciones.get(i) + nomina.get(i);
        return pagoTotal;
    }
    private static double calculosSegSocial(ArrayList<Empleado> empleados, int i){
        int auxilioTrans = 0;
        double salario30dias = 0;
        double valorHorasExtra = 0;
        double valorMensualidad = 0;
        int valorSegSocial = 0;

        if (empleados.get(i).auxilioTransporte) {
            auxilioTrans = 106454;
        }
        salario30dias = empleados.get(i).salario;
        valorHorasExtra = (salario30dias / 240) * empleados.get(i).horasExtra;
        valorMensualidad = ((salario30dias + auxilioTrans + valorHorasExtra));
        valorSegSocial = (int) ((valorMensualidad-auxilioTrans) * 0.21022);
        return valorSegSocial;
    }

    private static double calculosPrestaciones(ArrayList<Empleado> empleados, int i){
        double valorPrestaciones= 0;
        int auxilioTrans = 0;
        double salario30dias = 0;
        double valorHorasExtra = 0;
        double valorMensualidad = 0;
        double primaServCesantias = 0;

        if (empleados.get(i).auxilioTransporte) {
            auxilioTrans = 106454;
        }
        salario30dias = empleados.get(i).salario;
        valorHorasExtra = (salario30dias / 240) * empleados.get(i).horasExtra;
        valorMensualidad = ((salario30dias + auxilioTrans + valorHorasExtra));
        primaServCesantias = (valorMensualidad) * 0.0833;
        valorPrestaciones = (primaServCesantias + primaServCesantias*1.12 + (salario30dias * 0.0416));
        return valorPrestaciones;
    }

    private static double calculosNominaEmp(ArrayList<Empleado> empleados, int i) {
        int auxilioTrans = 0;
        int salario30dias = 0;
        int valorHorasExtra = 0;
        int paraFiscales = 0;
        int valorMensualidad = 0;

        if (empleados.get(i).auxilioTransporte) {
            auxilioTrans = 106454;
        }
        salario30dias = empleados.get(i).salario;
        valorHorasExtra = (salario30dias / 240) * empleados.get(i).horasExtra;
        paraFiscales = (int)((salario30dias + valorHorasExtra) * 0.08);
        valorMensualidad = ((salario30dias + auxilioTrans + valorHorasExtra)) - paraFiscales;
        return valorMensualidad;
    }

    private static double calculosParafiscales(ArrayList<Empleado> empleados, int i) {
        int auxilioTrans = 0;
        int salario30dias = 0;
        int valorHorasExtra = 0;
        //int paraFiscales = 0;
        int valorMensual = 0;
        int valorParafiscales = 0;

        if (empleados.get(i).auxilioTransporte) {
            auxilioTrans = 106454;
        }
        salario30dias = empleados.get(i).salario;
        valorHorasExtra = (salario30dias / 240) * empleados.get(i).horasExtra;
        //paraFiscales = ((salario30dias + valorHorasExtra) * 8)/100;
        valorMensual = ((salario30dias + auxilioTrans + valorHorasExtra));
        valorParafiscales = (valorMensual-auxilioTrans) * 9/100;
        return valorParafiscales;
    }
}