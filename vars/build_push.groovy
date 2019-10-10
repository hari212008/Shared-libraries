import com.devops.scm.git
import com.devops.ecr_build.ecr
import com.devops.terraform.tfscripts


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
		 println "test object:"
		 println "${build}" 
//                execute.setValue("${config.image_name}")
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

