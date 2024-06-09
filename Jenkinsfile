pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'trisceles-acs'
    }

    options {
        timeout(time: 2, unit: 'HOURS') // Aumenta o tempo limite para 2 horas
    }

    stages {
        stage('Inicial') {
            steps {
                echo 'Iniciando a pipeline'
            }
        }

        stage('Build Image') {
            steps {
                echo 'Iniciando a construção da imagem (Docker)'
                script {
                    // Verifica se o Docker está instalado e em execução
                    sh 'docker --version'
                    sh 'docker info'

                    // Constrói a imagem Docker
                    dockerapp = docker.build(DOCKER_IMAGE, '-f ./Dockerfile ./')
                }
            }
        }

        stage('Run Container') {
            steps {
                script {
                    docker.image(DOCKER_IMAGE).inside {
                        sh 'echo "Container is running"'
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}