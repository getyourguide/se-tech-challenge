<script>
import Activities from './components/Activities.vue'

export default {
  name: 'App',
  components: {
    Activities
  },
}
</script>

<template>
  <div id="app">
    <h1>Activities</h1>
    <Activities :activities="activities" />
  </div>
</template>

<style>
#app {
  text-align: center;
  color: #2c3e50;
}
</style>
