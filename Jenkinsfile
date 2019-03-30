node {
    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        sh "rm -rf build/libs/"
        sh "chmod +x gradlew"
        sh "./gradlew build --refresh-dependencies"
    }

    stage("publish") {
        when {
            branch 'master'
        }
        steps {
            sh "./gradlew publish"
        }
    }

    stage("gradle plugin") {
        when {
            branch 'master'
        }
        steps {
            withCredentials([file(credentialsId: 'gradlePluginProperties', variable: 'PROPERTIES')]) {
                sh '''
                    cat "$PROPERTIES" >> gradle.properties
                    ./gradlew publishPlugins
                    '''
            }
        }
    }

    stage("increaseBuildnumber") {
        steps {
            sh "./gradlew increaseBuildnumber"
        }
    }
}