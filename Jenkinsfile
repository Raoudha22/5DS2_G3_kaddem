pipeline {
    agent any
    stages {

        stage('Récupération du code') {
            steps {
                // Clone le dépôt de ton projet et sélectionne la branche.
                git branch: 'universtiy', url: 'https://github.com/Raoudha22/5DS2_G3_kaddem.git'
            }
        }
        stage('Nettoyage et compilation avec Maven') {
            steps {
                // Lancer Maven avec les commandes clean et compile
                sh 'mvn clean compile'
            }
        }

          stage('Test Unitaire') {
                    steps {
                        echo 'Exécution des tests unitaires : '
                        sh 'mvn test';
                    }
                }

            stage('SonarQube') {
            steps {
                echo 'Analyse de la Qualité du Code : ';
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=Tasnim_241001';
            }
        }

          stage('Maven Package') {
            steps {
                echo 'Création du livrable : ';
                sh 'mvn package -DskipTests';
            }
        }

         stage('Deploy to Nexus') {
            steps {
                echo 'Déploiement sur Nexus : '
                sh 'mvn deploy -DskipTests'
            }
        }

         stage('Image') {
            steps {
                echo 'Création Image : ';
                sh 'docker build -t tasnim2410/tp-foyer:1.0.0 .';
            }
        }

        stage('Dockerhub') {
            steps {
                echo 'Push Image to dockerhub : ';
               sh 'docker login -u tasnim2410 -p tasnim123';
              sh 'docker push tasnim2410/tp-foyer:1.0.0';
            }
        }

        stage('Docker-Compose') {
            steps {
                echo 'Start Backend + DB : ';
                sh 'docker compose up -d';
            }
        }

          stage('Lancer Prometheus') {
                    steps {
                        echo 'Lancement de Prometheus : '
                        sh 'docker run -d --name prometheus -p 9090:9090 prom/prometheus'
                    }
                }

                stage('Lancer Grafana') {
                    steps {
                        echo 'Lancement de Grafana : '
                        sh 'docker run -d --name grafana -p 3000:3000 grafana/grafana'
                    }
                }

    }
}
