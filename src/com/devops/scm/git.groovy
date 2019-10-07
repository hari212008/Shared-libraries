//com.maf.devops.scm.git
//Define Code checkout

package com.devops.scm

void setValue(String brname, String scm_url)
{
   this.branch_name = brname
   this.url = scm_url
} 

def codeCheckout()
{
wrap([$class: 'AnsiColorBuildWrapper']) {
    try {
      println "Branch name in src is " + branch_name
	cleanWs()
      println "\u001B[32m [INFO] Starting the code checkout. Please wait...\u001B[0m "
      checkout (
        changelog: false,
        poll: false,
        scm :[
        $class: 'GitSCM',
        branches: [[name: "${branch_name}"]],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [[url: "${url}"]]
        ]
        )
      println "\u001B[32m [INFO] \u001B[0m: Code checkout from \u001B[32m${branch_name}\u001B[0m is \u001B[32m SUCCESSFULL\u001B[0m."
    }
catch (Exception error) {
      println "\u001B[41m [ERROR] failed to Checkout the tag $branch_name."
      throw error
    }
  }
}
