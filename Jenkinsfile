pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '10'))
        disableConcurrentBuilds()
        timeout(time: 1, unit: 'HOURS')
        timestamps()
    }
    
    environment {
        AWS_ACCOUNT_ID = "383860290056"
        AWS_DEFAULT_REGION = "ap-south-1"
        VERSION = "${BUILD_NUMBER}"
        PROJECT = "mb-common"
        IMAGE_TAG = "$PROJECT:$VERSION"
        IMAGE_REPO_NAME = "mb-common-test"
        REPOSITORY_URI = "https://383860290056.dkr.ecr.ap-south-1.amazonaws.com/mb-common-test"
        
    }

    stages {
        stage('Build & Test') {
            steps {
                withMaven(options: [artifactsPublisher(), mavenLinkerPublisher(), dependenciesFingerprintPublisher(disabled: true), jacocoPublisher(disabled: true), junitPublisher(disabled: true)]) {
                sh "mvn clean deploy -DskipTests -Dmaven.clover.skip -Pdev"
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    sh "cat Dockerfile"
                    dockerImage = docker.build "${IMAGE_REPO_NAME}:${IMAGE_TAG}"
                }
            }
        }

        stage('Push Image to ECR') {
            steps {
                    script {
                        sh "docker tag ${IMAGE_REPO_NAME}:${IMAGE_TAG} ${REPOSITORY_URI}:$IMAGE_TAG"
                        sh "docker push ${AWS_ACCOUNT_ID}.dkr.ecr.${AWS_DEFAULT_REGION}.amazonaws.com/${IMAGE_REPO_NAME}:${IMAGE_TAG}"
                    }
            }
        }
    }
}
