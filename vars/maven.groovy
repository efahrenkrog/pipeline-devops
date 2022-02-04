/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/
def call(stages){
  stage("Paso 1: Compliar"){
    sh "mvn clean compile -e"
  }
  
}
return this;