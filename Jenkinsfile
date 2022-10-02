pipeline {
    agent any
    tools {
        maven 'MAVEN_3_6_3'
        jdk 'JDK_1_11'
    }

    stages {
        stage ('Compile Stage eRentCar') {

            steps {
                withMaven(maven : 'MAVEN_3_6_3') {
                    sh 'mvn clean compile'
                }
            }
        }

        stage ('Testing Stage eRentCar') {

            steps {
                withMaven(maven : 'MAVEN_3_6_3') {
                    sh 'mvn test'
                }
            }
        }

        stage ('package Stage') {
            steps {
                withMaven(maven : 'MAVEN_3_6_3') {
                    sh 'mvn package'
                }
            }
        }

}