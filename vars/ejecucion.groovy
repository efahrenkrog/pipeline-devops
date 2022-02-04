def call(){
lib {
      agent any
      environment {
          NEXUS_USER         = credentials('NEXUS-USER')
          NEXUS_PASSWORD     = credentials('NEXUS-PASS')
      }
      parameters {
          choice(name:'compileTool',choices: ['Maven', 'Gradle'],description: 'Seleccione herramienta de compilacion')
          text description: 'ingrese los stages que se desean ejecutar, separados por ",",  dejar vacio para ejecutar todos los stage', name: 'stages'
      }
      stages {
          stage("Pipeline"){
              steps {
                  script{
                    switch(params.compileTool)
                      {
                          case 'Maven':
                              //def ejecucion = load 'maven.groovy'
                              //ejecucion.call()
                              maven.call()
                          break;
                          case 'Gradle':
                              //def ejecucion = load 'gradle.groovy'
                              //ejecucion.call()
                              gradle.call()
                          break;
                      }
                  }
              }
              post{
                  success{
                      slackSend color: 'good', message: "[Ele] [${JOB_NAME}] [${BUILD_TAG}] Ejecucion Exitosa", teamDomain: 'dipdevopsusac-tr94431', tokenCredentialId: 'Slack-Tocken'
                  }
                  failure{
                      slackSend color: 'danger', message: "[Ele] [${env.JOB_NAME}] [${BUILD_TAG}] Ejecucion fallida en stage [${env.TAREA}]", teamDomain: 'dipdevopsusac-tr94431', tokenCredentialId: 'Slack-Tocken'
                  }
              }
          }
      }
  }
}
return this