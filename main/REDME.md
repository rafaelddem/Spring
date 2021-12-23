# Documentação

### Index

A página inicial não apresenta nenhuma funcionalidade, foi mantida apenas por comodidade
link: http://localhost:8080/


### Carregar dados

A página em questão carrega os dados automaticamente, assim que chamada. A função que faz a leitura dos dados, busca na pasta padrão (pasta "files" dentro da raiz do projeto) os arquivos que serão carregados
link: http://localhost:8080/loadData

Obs. ¹: O sistema valida a possbilidade de um mesmo produtor aparecer mais de uma vez, porém não faz o mesmo para filmes, pois partiu-se do princípio que um mesmo filme não será salvo mais de uma vez.
Obs. ²: Sempre que o link em questão for chamado, os arquivos da pasta serão carregados. Para evitar duplicação dos dados, reinicie o sistema antes de testar um mesmo arquivo


### Apresentação dos resultados

A página em questão faz a busca dos produtores que satisfazem o solicitado, ou seja, aqueles que possuem o maior período entre dois premios consecutivos, e os que possuem o menor período
link: http://localhost:8080/statitics