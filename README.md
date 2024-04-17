# inventory-management-system

#### This API design complies with [![OpenAPI Specification v3.1.0](https://spec.openapis.org/oas/v3.1.0)](https://spec.openapis.org/oas/v3.1.0)
#### Try it out --> [https://app.swaggerhub.com/apis/KHALILKKHAWAJA/InventoryManagementSystem/1.0.0](https://app.swaggerhub.com/apis/KHALILKKHAWAJA/inventory-management_system_ims_open_api_3_1_0/1.0.0#/items/post_items)


## Entity Relationship Diagram

![Basic ERD](https://github.com/Theneroc/inventory-management-system/blob/main/IMS%20-%20Basic%20ERD.png)

## Resources

### Resource Description

#### Item:
The core resource of the system. All items will have unique identifiers and foreign keys referencing location instances. Locations will have a composite relationship with items, if a location is removed from the database all items associated with the location will also be removed. An item instance will contain the cost of the item, as well as its markup (e.g. a drink costs 2 of a currency to buy and is sold at a markup of 1.5 = [2 x 1.5] making its total price 3 currency) for operational use.<br>

`id`: unique primary key as an integer<br>
`location_id`: foreign key to location instance as an integer (many-to-many)<br>
`name`: the name of the item as a string<br>
`cost`: the cost of an item before adding the profit margin as a double<br>
`markup`: the profit margin percentage of an item as a float<br>
`qty`: the number of items existing (e.g. 100 bottles of 2L water or could be 1 case of water containing 8 bottles --> 8 being the qty) as an integer<br>
`expiration`: the date at which the item expires (YYYY-MM-DD)<br>

#### Location:
The location resource will provide information on where the items will be physically stored as well as contact information.<br>

`id`: primary key as an integer<br>
`address`: physical location of the location instance as a string<br>
`email_address`: email address of the location (if it is an external location from the inventory itself) as a string<br>
`phone_num`: phone number of the location (if it is an external location from the inventory itself) as a string<br>


#### Import:
The imports are representations of transactions being imported into the inventory. Each import instance will have a foreign key of the item they will physically contain. An import will have an aggregate relationship with an item imported into the inventory as well as the location id of where the item was imported to. They will also contain further specifications as to the cost of the import.

`id`: primary key as an integer<br>
`item_id`: foreign key to a specific item (one-to-one) as an integer<br>
`location_id`: foreign key to a specific location (one-to-one) as an integer<br>
`cost`: the cost of importing to the inventory (like a purchase) as a double<br>
`qty`: the amount of the specific item within the transaction (e.g. 5 cases of water)<br>
`transaction_date`: the date the import took place (YYYY-MM-DD)<br>


#### Export:
The exports are representations of transactions being imported into the inventory. Each export instance will have a foreign key of the item they will physically contain. An export will have an aggregate relationship with an item exported from the inventory as well as the location id of where the item was exported from. They will also contain further specifications as to the income received from the export.

`id`: primary key as an integer<br>
`item_id`: foreign key to a specific item (one-to-one) as an integer<br>
`location_id`: foreign key to a specific location (one-to-one) as an integer<br>
`income`: the income of exporting from the inventory (like a sale) as a double<br>
`qty`: the amount of the specific item within the transaction (e.g. 5 cases of water)<br>
`transaction_date`: the date the import took place (YYYY-MM-DD)<br>
<br>
#### Further documentation on the implementation is expressed within the code itself  - Goto: (`InventoryManagementSystemApplication.class`)<br>

####Testing of the API is accessible via the Postman file within the repo `IMS REST CRUD API.postman_collection.json`
### Tables

<span class="c0"></span>

<a id="t.f2265a2f00cc4020d1ba4e121facb4970d52e7fc"></a><a id="t.0"></a>

<table class="c16">

<tbody>

<tr class="c21">

<td class="c24 c20" colspan="6" rowspan="1">

<span class="c13 c2">Collection Resource (/items)</span>

</td>

</tr>

<tr class="c11">

<td class="c4 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP request</span>

<span class="c17 c2">(method URI)</span>

</td>

<td class="c8 c15" colspan="1" rowspan="1">

<span class="c17 c2">Operation</span>

</td>

<td class="c12 c15" colspan="1" rowspan="1">

<span class="c17 c2">Description</span>

</td>

<td class="c5 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP status codes</span>

</td>

<td class="c9 c15" colspan="1" rowspan="1">

<span class="c17 c2">Request sample</span>

</td>

<td class="c3 c15" colspan="1" rowspan="1">

<span class="c2 c17">Response sample</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">POST /items</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Create</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Creates an item resource</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">201</span><span class="c0">: Created, creates a list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

<span class="c0"></span>

<span class="c0"></span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">POST /items</span>

<span class="c0">{</span>

<span class="c0">location_id: 1,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 4,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 201</span>

<span>Created</span>

<span class="c0">{</span>

<span class="c0">id: 1</span>

<span class="c0">location_id: 1,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 4,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">GET /items</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all items</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /items</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of items]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">GET</span>

<span class="c0">/items/{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves an item with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns item</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">Get /items/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1</span>

<span class="c0">location_id: 1,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 4,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">GET</span>

<span class="c0">/items/</span>

<span class="c0">{name}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Read by name</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves an item with a specific name</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns item</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">Get /item/coke 2L</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1</span>

<span class="c0">location_id: 1,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 4,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">PUT</span>

<span class="c0">/items/{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Update/</span>

<span class="c0">Replace</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Updates or replaces an item with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, updates item resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PUT /items/1</span>

<span class="c0">{</span>

<span class="c0">id: 1</span>

<span class="c0">location_id: 2,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 5,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1</span>

<span class="c0">location_id: 2,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 5,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/items/{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Partial Update/Modify</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Partially updates or modifies an item with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, partially updates item resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/items/1</span>

<span class="c0">{cost: 4.5}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1</span>

<span class="c0">location_id: 2,</span>

<span class="c0">name: “coke 2L”,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">markup: 0.1,</span>

<span class="c0">qty: 1,</span>

<span class="c0">expiration: 1/1/2025</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/items/{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Delete</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Deletes an item with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">204</span><span class="c0">: No Content, item resource deleted</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/items/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 204</span>

<span class="c0">No Content</span>

</td>

</tr>

</tbody>

</table>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<a id="t.0e579db4b56a9df6be4f4338283d1184ae701109"></a><a id="t.1"></a>

<table class="c16">

<tbody>

<tr class="c21">

<td class="c24 c20" colspan="6" rowspan="1">

<span class="c13 c2">Collection Resource (/locations)</span>

</td>

</tr>

<tr class="c11">

<td class="c4 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP request</span>

<span class="c17 c2">(method URI)</span>

</td>

<td class="c8 c15" colspan="1" rowspan="1">

<span class="c17 c2">Operation</span>

</td>

<td class="c12 c15" colspan="1" rowspan="1">

<span class="c17 c2">Description</span>

</td>

<td class="c5 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP status codes</span>

</td>

<td class="c9 c15" colspan="1" rowspan="1">

<span class="c17 c2">Request sample</span>

</td>

<td class="c3 c15" colspan="1" rowspan="1">

<span class="c17 c2">Response sample</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">POST /locations</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Create</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Creates a location resource</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">201</span><span class="c0">: Created, creates a list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

<span class="c0"></span>

<span class="c0"></span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">POST /locations</span>

<span class="c0">{</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4444”</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 201</span>

<span class="c0">Created</span>

<span class="c0">{</span>

<span class="c0">id: 1,</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4444”</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c10">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">GET /locations</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all locations</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /locations</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of locations]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">GET</span>

<span class="c0">/locations/</span>

<span class="c0">{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves an location with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns location</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">Get /location/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1,</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4444”</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">GET</span>

<span class="c0">/locations/</span>

<span class="c0">{address}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Read by address</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves an location with a specific address</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns location</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">Get /location/Ramallah, Al-Bireh</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1,</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4444”</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">PUT</span>

<span class="c0">/locations</span>

<span class="c0"></span>

<span class="c0">/{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Update/</span>

<span class="c0">Replace</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Updates or replaces a location with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, deletes location resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PUT /location/1</span>

<span class="c0">{</span>

<span class="c0">id: 1,</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini2@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4445”</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1,</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini2@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4445”</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/locations/</span>

<span class="c0">{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Partial Update/Modify</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Partially updates or modifies a location with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, partially updates location resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/locations/1</span>

<span class="c0">{phone_num: 02-293-4444}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">id: 1,</span>

<span class="c0">address: “Ramallah, Al-Bireh”,</span>

<span>email_address: “</span><span class="c6">[alshini@gmail.com](mailto:alshini@gmail.com)</span><span class="c0">”,</span>

<span class="c0">phone_num: “02-293-4444”</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c4" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/locations</span>

<span class="c0">/{id}</span>

</td>

<td class="c8" colspan="1" rowspan="1">

<span class="c0">Delete</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Deletes a location with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">204</span><span class="c0">: No Content</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/locations/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 204</span>

<span class="c0">No Content</span>

</td>

</tr>

</tbody>

</table>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<a id="t.f5f753dc8a9980a2281a09f25180ab7c028c215e"></a><a id="t.2"></a>

<table class="c16">

<tbody>

<tr class="c21">

<td class="c20 c24" colspan="6" rowspan="1">

<span class="c13 c2">Collection Resource (/imports)</span>

</td>

</tr>

<tr class="c11">

<td class="c19 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP request</span>

<span class="c17 c2">(method URI)</span>

</td>

<td class="c18 c15" colspan="1" rowspan="1">

<span class="c17 c2">Operation</span>

</td>

<td class="c12 c15" colspan="1" rowspan="1">

<span class="c17 c2">Description</span>

</td>

<td class="c5 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP status codes</span>

</td>

<td class="c9 c15" colspan="1" rowspan="1">

<span class="c17 c2">Request sample</span>

</td>

<td class="c3 c15" colspan="1" rowspan="1">

<span class="c17 c2">Response sample</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">POST /imports</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Create</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Creates a imports resource</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">201</span><span class="c0">: Created, creates a list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

<span class="c0"></span>

<span class="c0"></span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">POST /imports</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">location_id: 1,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">qty: 1,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 201</span>

<span class="c0">Created</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">location_id: 1,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">qty: 1,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /imports</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all imports</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /imports</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of imports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /imports/day</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all imports that occurred within a day (24 hrs)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">day</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of imports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">week</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all imports that occurred within a week (7 days)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">week</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of imports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">month</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all imports that occurred within a month (~30 days)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">month</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of imports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">year</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all imports that occurred within a week (~365 days)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /imports/</span>

<span class="c0">year</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of imports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET</span>

<span class="c0">/imports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves an imports with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns imports</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">Get /imports/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">qty: 1,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">PUT</span>

<span class="c0">/imports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Update/</span>

<span class="c0">Replace</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Updates or replaces a import with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, deletes import resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PUT /import/1</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 9,</span>

<span class="c0">qty: 2,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 9,</span>

<span class="c0">qty: 2,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/imports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Partial Update/Modify</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Partially updates or modifies an import with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, partially updates import resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/imports/1</span>

<span class="c0">{transaction_date:</span>

<span class="c0">2/3/2024}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 9,</span>

<span class="c0">qty: 2,</span>

<span class="c0">transaction_date:</span>

<span class="c0">2/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/imports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Delete</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Deletes an import with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">204</span><span class="c0">: No Content</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/imports/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 204</span>

<span class="c0">No Content</span>

</td>

</tr>

</tbody>

</table>

<span class="c0"></span>

<span class="c0"></span>

<span class="c0"></span>

<a id="t.47df9c1188078fc157879aebdd1fb2d043ce674b"></a><a id="t.3"></a>

<table class="c16">

<tbody>

<tr class="c21">

<td class="c24 c20" colspan="6" rowspan="1">

<span class="c2 c13">Collection Resource (/exports)</span>

</td>

</tr>

<tr class="c11">

<td class="c19 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP request</span>

<span class="c17 c2">(method URI)</span>

</td>

<td class="c15 c18" colspan="1" rowspan="1">

<span class="c17 c2">Operation</span>

</td>

<td class="c12 c15" colspan="1" rowspan="1">

<span class="c17 c2">Description</span>

</td>

<td class="c5 c15" colspan="1" rowspan="1">

<span class="c17 c2">HTTP status codes</span>

</td>

<td class="c9 c15" colspan="1" rowspan="1">

<span class="c17 c2">Request sample</span>

</td>

<td class="c3 c15" colspan="1" rowspan="1">

<span class="c17 c2">Response sample</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">POST /exports</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Create</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Creates a exports resource</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">201</span><span class="c0">: Created, creates a list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

<span class="c0"></span>

<span class="c0"></span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">POST /exports</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">location_id: 1,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">qty: 1,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 201</span>

<span class="c0">Created</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">location_id: 1,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">qty: 1,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /exports</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all exports</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /exports</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of exports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /exports/day</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all exports that occurred within a day (24 hrs)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">day</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of exports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">week</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all exports that occurred within a week (7 days)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">week</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of exports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">month</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all exports that occurred within a month (~30 days)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">month</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of exports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">year</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves a list of all exports that occurred within a week (~365 days)</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns list</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">GET /exports/</span>

<span class="c0">year</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">[list of exports]</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">GET</span>

<span class="c0">/exports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Read</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Retrieves an exports with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, returns exports</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">Get /exports/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 4.5,</span>

<span class="c0">qty: 1,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">PUT</span>

<span class="c0">/exports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Update/</span>

<span class="c0">Replace</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Updates or replaces a export with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">204</span><span class="c0">: No content, export resource deleted</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PUT /export/1</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 9,</span>

<span class="c0">qty: 2,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 9,</span>

<span class="c0">qty: 2,</span>

<span class="c0">transaction_date:</span>

<span class="c0">1/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/exports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Partial Update/Modify</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Partially updates or modifies an export with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">200</span><span class="c0">: OK, partially updates export resource</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">PATCH</span>

<span class="c0">/exports/1</span>

<span class="c0">{transaction_date:</span>

<span class="c0">2/3/2024}</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 200</span>

<span class="c0">OK</span>

<span class="c0">{</span>

<span class="c0">item_id: 1,</span>

<span class="c0">cost: 9,</span>

<span class="c0">qty: 2,</span>

<span class="c0">transaction_date:</span>

<span class="c0">2/3/2024</span>

<span class="c0">}</span>

</td>

</tr>

<tr class="c11">

<td class="c19" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/exports/{id}</span>

</td>

<td class="c18" colspan="1" rowspan="1">

<span class="c0">Delete</span>

</td>

<td class="c12" colspan="1" rowspan="1">

<span class="c0">Deletes an export with a specific id</span>

</td>

<td class="c5" colspan="1" rowspan="1">

<span class="c2">204</span><span class="c0">: No Content, export resource deleted</span>

<span class="c2">400</span><span class="c0">: Bad request</span>

<span class="c2">401</span><span class="c0">: Unauthorized</span>

<span class="c2">403</span><span class="c0">: Forbidden</span>

<span class="c2">500</span><span class="c0">: Internal Server Error</span>

<span class="c2">503</span><span class="c0">: Service Unavailable</span>

</td>

<td class="c9" colspan="1" rowspan="1">

<span class="c0">DELETE</span>

<span class="c0">/exports/1</span>

</td>

<td class="c3" colspan="1" rowspan="1">

<span class="c0">HTTP/1.1 204</span>

<span class="c0">No Content</span>

</td>

</tr>

</tbody>

</table>

<span class="c0"></span>

