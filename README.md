# ixigo-api-automation
To run the test case for searching top 20 cheapest flight using Ixigo flight search api

Execute command from command line from the root of this project : "mvn clean verify"

The output will be printed into the console and also being pushed into the logs file at the path : "/target/application-log.log"

The more intuitive extent report can also be found for the test execution at the path : "/target/extent-report/report.html"
 
API curl :-
	curl --location --request GET 'https://www.ixigo.com/api/flights/search/poll/1q4h30lhdnpntphtupntphdhphphqh8ojqh96ohtpputptpdtspdndvk?searchProviderIds=1026' \
	--header 'authority: www.ixigo.com' \
	--header 'deviceid: af76be22d4f14a9b96a9' \
	--header 'x-requested-with: XMLHttpRequest' \
	--header 'clientid: ixiweb' \
	--header 'apikey: ixiwebu00212$' \
	--header 'uuid: af76be22d4f14a9b96a9' \
	--header 'user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.105 Safari/537.36' \
	--header 'ixisrc: ixiweb' \
	--header 'accept: */*' \
	--header 'sec-fetch-site: same-origin' \
	--header 'sec-fetch-mode: cors' \
	--header 'sec-fetch-dest: empty' \
	--header 'referer: https://www.ixigo.com/search/result/flight?from=DEL&to=BOM&date=19092020&returnDate=28092020&adults=1&children=0&infants=0&class=e&source=Modify%20Search%20Form' \
	--header 'accept-language: en,en-US;q=0.9,hi;q=0.8' \
	--header 'cookie: __cfduid=d45a7c8350af58302065db1d5b6ebe0461597903973; _gid=GA1.2.897691834.1597903975; ncr=IN; ixiUsrLocale="urgn=Maharashtra:ucnc=IN:ucty=Mumbai:cnc=IN:cc=INR:lng=en"; WZRK_G=fa9f2cceb94a4be2a894a3c3f9be411a; ixiUID=af76be22d4f14a9b96a9; ixiSrc="pGFkARw54WJGtOBMEUCqkXw3GXOu2hu4D4W6Ih7hhpO9L7/4WRfBxMK7AZ/zVyQEMLX4HbQdS1zFxcDiBHJe82GSz0sUBRTMeH8gprwX0hk="; ixigoSrc="af76be22d4f14a9b96a9|SEO-goog:20082020|SEO-goog:20082020|SEO-goog:20082020"; _fbp=fb.1.1597903975621.576036740; g_state={"i_p":1597912318003,"i_l":1}; G_ENABLED_IDPS=google; __gads=ID=877c065f1c9244d0:T=1597905171:S=ALNI_MZzWnVLxrG6IhhrdXBQBci5YNhPQQ; _ga_LJX9T6MDKX=GS1.1.1597903975.1.1.1597905817.59; _ga=GA1.2.1865463842.1597903975; WZRK_S_R5Z-849-WZ4Z=%7B%22p%22%3A4%2C%22s%22%3A1597903976%2C%22t%22%3A1597905818%7D; _gat_UA-949229-1=1; ixiUID=0e712a3ee5e44afbbe7b; ixiSrc="E47JsANgtBsYWgJ81w+bEM8HYLalnEi8RWr19xS+8Fg3yKeVcx5kw3/JcrqjCNYJuaFRawAkXB7XRfMuuWehWQ=="; ixigoSrc="0e712a3ee5e44afbbe7b|DIR:06072020|DIR:06072020|DIR:06072020"' 

I have simulated the complete api request into the framework but due to some unknown reason it is throwing 400 Bad request with response as "INVALID_API_KEY"
so that I have kept the mocked response from web site into the "response.txt" file at the test resources location "/src/test/resources/Response.txt"
and parsing the response for validating or getting the top 20 cheapest flights for the round trip of a person as per the assignment statement.

Problem Statement

For any domestic round trip combination, generate a list of the top 20 cheapest flights combination on ixigo website using the UI/API automation script.

Sample Url: https://www.ixigo.com/search/result/flight/DEL/BLR/13052020/20052020/1/0/0/e?source=Search%20Form

Website: https://www.ixigo.com/
Departure Date : current date + 30 days (03042020)
Arrival Date : current date + 40 days(13042020)
No. of Adults : 1
Source/Destination : any Indian city( DEL, BLR, BOM,etc )   
	