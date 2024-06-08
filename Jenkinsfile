pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'alexanderixaeon/trisceles-acs:latest'
    }

    options {
        timeout(time: 2, unit: 'HOURS')
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
                    sh 'docker --version'
                    sh 'docker info'

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