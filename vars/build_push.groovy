import com.devops.scm.git
import com.devops.ecr_build.ecr

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Build and push --> ECR')
     {
       try {
           def build = new ecr()
                build.push()
        }
       catch (Exception error)
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error} Pushing ECR Image"
               throw error
                                                        }
             }
      }
}

