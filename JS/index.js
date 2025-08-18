altura = 170
peso = 61.0
nalt=altura/100
imc=peso/(nalt*nalt)

let resultado = imc < 16 ? "Baixo peso muito grave" :
                imc >= 16 && imc <= 16.99 ? "Baixo peso grave" :
                imc >= 17 && imc <= 18.49 ? "Baixo peso" :
                imc >= 18.50 && imc <= 24.99 ? "Peso normal":
                imc >= 25 && imc <= 29.99 ? "Sobrepeso":
                imc >= 30 && imc <= 34.99 ? "Obesidade grau I":
                imc >= 35 && imc <= 39.99 ? "Obesidade grau II":
                imc >= 40 ? "Obesidade grau III":
                "invalido";
console.log(imc.toFixed(2));
console.log(resultado)


