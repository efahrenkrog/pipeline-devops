/*
	forma de invocación de método call:
	def ejecucion = load 'script.groovy'
	ejecucion.call()
*/
def call(stages){
  stage("Paso 1: Compliar"){
    sh "mvn clean compile -e"
  }
  
  stage("Paso 2: Sonar - Análisis Estático"){
      sh "echo 'Análisis Estático!'"
      withSonarQubeEnv('sonarqube') {
          sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=ejemplo-gradle -Dsonar.java.binaries=build'
      }
  }
}
return this;