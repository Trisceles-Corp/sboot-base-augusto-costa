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
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh "docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD"
                    sh "docker push ${DOCKER_IMAGE}:${env.BUILD_ID}"
                    sh "docker push ${DOCKER_IMAGE}:latest"
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