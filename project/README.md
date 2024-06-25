# Smart Quiz
<img title="Preview" alt="Preview" src="./preview.png">

## Deploy (no qrcode)
Per eseguire i container:
```sh
docker-compose up
```

## Deploy
Per abilitare la funzionalità di condivisione del quiz tramite qrcode nella stessa rete, occorre fornire come parametro di ingresso l'indirizzo IP dell'ambiente Docker utilizzato.
```sh
API_HOST=YOUR_LOCAL_IP docker-compose up
```
Ad esempio, se sulla macchina di esecuzione è associato l'indirizzo IP 192.168.1.102:
```sh
API_HOST=192.168.1.102 docker-compose up
```