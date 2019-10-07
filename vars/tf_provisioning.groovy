import com.devops.terraform.tfscripts
mport com.devops.terraform.tfplan
def call(body)
{
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

     stage('Starting TF initialization')
     {
       try {
           def provision  = new tfscripts()
	       provision.tfInit()
			
             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error}  TF initialization"
               throw error
                                                        }
             }
      }
	catch (Exception error) {
      println "\u001B[41m [ERROR] failed to plan terraform."
      throw error
    }
}
stage('Starting TF initialization')
     {
       try {
           def planning  = new tfplan()
              planning.tfplan()

             {
               wrap([$class: 'AnsiColorBuildWrapper']) {
               echo "\u001B[41m[ERROR] ${error}  TF initialization"
               throw error
                                                        }
             }
      }
        catch (Exception error) {
      println "\u001B[41m [ERROR] failed to plan terraform."
      throw error
    }
}

}

