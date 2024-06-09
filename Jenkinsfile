pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'trisceles-acs'
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

                    dockerapp = docker.build("${DOCKER_IMAGE}:${env.BUILD_ID}", '-f ./Dockerfile ./')
                }
            }
        }

        stage('Push Image') {
            steps {
                echo 'Pushing image.'
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                        dockerapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }

//         stage('Run Container') {
//             steps {
//                 script {
//                     docker.image(DOCKER_IMAGE).inside {
//                         sh 'echo "Container is running"'
//                     }
//                 }
//             }
//         }
    }

//     post {
//         always {
//             cleanWs()
//         }
//     }
}