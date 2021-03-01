<?php

/** @var \Laravel\Lumen\Routing\Router $router */

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

$router->get('/', function () use ($router) {
    return $router->app->version();
});


$router->group(["prefix" => "api/people"], function () use ($router) {

    $router->get('list', "PeopleController@list");
    $router->get('listOne', "PeopleController@listOne");
    $router->post('create', "PeopleController@create");
    $router->post('update', "PeopleController@update");
    $router->post('delete', "PeopleController@delete");

});
