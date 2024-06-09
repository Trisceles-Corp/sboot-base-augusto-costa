pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'augusto-costa-acs'
        DOCKER_REGISTRY = 'registry.hub.docker.com'
        DOCKER_REPO = 'alexanderixaeon/augusto-costa-acs'
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
                    docker.withRegistry("https://${DOCKER_REGISTRY}", 'dockerhub') {
                        sh "docker tag ${DOCKER_IMAGE}:${env.BUILD_ID} ${DOCKER_REPO}:${env.BUILD_ID}"
                        sh "docker push ${DOCKER_REPO}:${env.BUILD_ID}"
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