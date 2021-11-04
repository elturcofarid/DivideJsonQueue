pipeline {
  agent any

  environment {
    APP_NAME = "056980729261.dkr.ecr.us-east-1.amazonaws.com/utilitario-divide-json"
    CONTAINER_NAME = "SERVICIO-DIVIDE-JSON"
  }

  stages {

    stage('Package') {
      steps {
        echo "-=- packaging project -=-"
        sh "sudo mvn clean package -Dmaven.test.skip=true"
      }
    }
    
    
    stage('Delete Docker Container') {
      steps {
        script {
          try {
            echo "-=- delete Docker container -=-"
            sh "sudo docker stop  ${CONTAINER_NAME}"
            sh "sudo docker rm  ${CONTAINER_NAME}"
            sh "sudo docker image rm ${APP_NAME}"
          } catch (Exception e) {
            echo "-=- -=-"
          }
        }
      }
    }

    stage('Build Docker image') {
      steps {
        echo "-=- build Docker image -=-"
        sh "sudo docker build -t ${APP_NAME} ."
      }
    }

    stage('Run Docker image') {
      steps {
        echo "-=- run Docker image -=-"
        sh "sudo docker run -d --name ${CONTAINER_NAME} -e SPRING_PROFILES_ACTIVE=pru --network spring  ${APP_NAME}"

      }
    }
    
    
     stage('Push Docker image') {
      steps {
        echo "-=- Push Docker image -=-"      
              sh "sudo aws ecr get-login-password --region us-east-1 | sudo docker login --username AWS --password-stdin 056980729261.dkr.ecr.us-east-1.amazonaws.com"
              sh "sudo docker push ${APP_NAME}"
      }
    }
  }
}