pipeline{
    agent any
    stages{
        stage('Inicial'){
            steps{
                echo 'Iniciando a pipeline'
            }
        }
        stage('Build Image'){
            steps{
                echo 'Iniciando a construçaõ da imagem (Docker)'
                script{
                    dockerapp = docker.build("alexanderixaeon/trisceles-acs", '-f ./Dockerfile ./')
                }
            }
        }
    }
}