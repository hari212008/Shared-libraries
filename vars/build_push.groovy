import com.devops.scm.git
import com.devops.ecr_build.ecr

def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Copying EDA&Data processing scripts')
     {
       try {
           def image = new ecr()
		image.setValue("${config.image_name}")               
		 image.push()
        }
       catch (Exception error)
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error} copying EDA&DATA"
               throw error
                                                        }
             }
      }
}

