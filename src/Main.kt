fun main() {


    // El cálculo del índice de masa
    //corporal se hace con la siguiente formula: IMC = Peso (kg) / (Estatura (m) x
    //Estatura (m)).
    println("-------------------------Bienvenido! -----------------------------------\n")
    println("-------------------------VAMOS A VER CÓMO ESTÁS DE PESO SEGÚN EL IMC------------------------------ \n")
    println("Ingresa tu peso:")
    var peso = readln().toDouble()

    println("Ingresa tu Estatura:")
    var Estatura = readln().toDouble()
    var MasaCorporal = peso / (Estatura * Estatura)
    var icm = 0

    println("Tu índice de Masa Corporal es: ${MasaCorporal} \n")
    println("Tu clasificación del ICM ES:")

    if(MasaCorporal >= 16.01 && MasaCorporal <= 18.50){
        println("Eres de bajo peso")
    }
    else if(MasaCorporal < 16.00) {
        println("Tienes Delgadez Severa \uD83D\uDE33 Come más !")
    } else if (MasaCorporal >= 16.00 && MasaCorporal <= 16.99) {
        println("Tienes Delgadez Moderada \uD83D\uDE2F Más proteína ")
    } else if (MasaCorporal >= 17.00 && MasaCorporal <= 18.49) {
        println("Tienes Delgadez Leve \uD83D\uDE26")
    } else if (MasaCorporal >= 18.5 && MasaCorporal <= 24.99) {
        println("Estas Normal \uD83D\uDE04")
    }else if (MasaCorporal == 25.00) {
        println("Tienes Sobrepeso \uD83E\uDD79 ")
    }else if (MasaCorporal >= 25.00 && MasaCorporal <= 29.99) {
        println("Estas Preobeso \uD83D\uDE22")
    }else if (MasaCorporal == 30.00) {
        println("Obesidad \uD83E\uDD7A")
    }else if (MasaCorporal >= 30.00 && MasaCorporal <= 34.99) {
        println("Estas En Obesidad leve \uD83D\uDE2C ")
    }else if (MasaCorporal >= 35.00 && MasaCorporal <= 39.99) {
        println("Estas En Obesidad Media \uD83D\uDE2C ")
    } else if (MasaCorporal >= 40.00) {
        println("Estas En Obesidad Mórbida \uD83D\uDE28")
    }

}