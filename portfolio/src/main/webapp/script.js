// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomFact() {
  const greetings =
      ['Hello world!', '¡Hola Mundo!', '你好，世界！', 'Bonjour le monde!'];

  const facts = 
      ['I cannot swallow pills' , 'I do not know how to ride a bike', 'I have 2 dogs', 'I have watched every episode of Gossip Girl at least four times.', 'I was hungry as I was typing these facts.']

  // Pick a random fact.
  const fact = facts[Math.floor(Math.random() * facts.length)];

  // Add it to the page.
  const factContainer = document.getElementById('fact-container');
  factContainer.innerText = fact;
}

function requestHello() {
   fetch('/data').then(response => response.text()).then((hello)=> {
    document.getElementById('hello-container').innerText = hello;
    location.replace('/data');
    addToDom(hello);
  });
  /*const response = await fetch('/data');
  const hello = await response.text();
  document.getElementById('hello-container').innerText = hello;
  location.replace('/data')
  addToDom(hello)*/
}

function addToDom(hello) {
  console.log('Adding msg to dom: ' + hello);
  const helloContainer = document.getElementById('hello-container');
  helloContainer.innerText = hello;
}