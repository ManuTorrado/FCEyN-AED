package aed;

class Funciones {
    int cuadrado(int x) {
        return x * x;
    }

    double distancia(double x, double y) {
        double cuadradoX = x * x;
        double cuadradoY = y * y;
        double sumaDeCuadrados = cuadradoX + cuadradoY;
        double res = Math.sqrt(sumaDeCuadrados);
        return res;
    }

    boolean esPar(int n) {
        return (n % 2 == 0);
    }

    boolean esBisiesto(int n) {

        if (n % 400 == 0) {
            return true;
        }

        if (n % 4 == 0 && !(n % 100 == 0)) {
            return true;
        }
        return false;
    }

    int factorialIterativo(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res = res * i;

        }
        return res;
    }

    int factorialRecursivo(int n) {

        if (n == 1 || n == 0) {
            return 1;
        }
        return n * factorialRecursivo(n - 1);
    }

    boolean esPrimo(int n) {

        if (n == 0 || n == 1)
            return false;

        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }

        }
        return true;
    }

    int sumatoria(int[] numeros) {

        int res = 0;
        for (int i : numeros) {
            res = res + i;

        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == buscado)
                return i;

        }
        return -1;
    }

    boolean tienePrimo(int[] numeros) {
        for (int i : numeros) {
            if (esPrimo(i))
                return true;

        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int i : numeros) {
            if (!esPar(i)) {
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;

        for (int i = 0; i < s1.length(); i++) {
            if (!(s1.charAt(i) == s2.charAt(i)))
                return false;

        }

        return true;
    }

    boolean esSufijo(String s1, String s2) {

        if (s1.length() > s2.length())
            return false;

        for (int i = 1; i < s1.length() + 1; i++) {
            if (!(s1.charAt(s1.length() - i) == s2.charAt(s2.length() - i)))
                return false;

        }
        return true;
    }
}
