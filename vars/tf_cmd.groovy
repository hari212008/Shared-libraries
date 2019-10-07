def call(Map stageParams) {
    for (element in mapToList(stageParams)) {
        sh "terraform ${element[1]}"
    }
}

// Required due to JENKINS-27421
@NonCPS
def List<List<?>> mapToList(Map map) {
  return map.collect { it ->
    [it.key, it.value]
  }
} 
 

