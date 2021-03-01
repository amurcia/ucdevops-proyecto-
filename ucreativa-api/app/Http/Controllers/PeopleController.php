<?php

/**
 * Clase CRUD para la tabla people de la base de datos de personas de starwars
 * @author  Alvaro Murcia C
 */

namespace App\Http\Controllers;

use App\People;
use Illuminate\Http\Request;

class PeopleController extends Controller
{
    /**
     * Create a new controller instance.
     *
     * @return void
     */

    private $people;
    private $msg;
    private $error;
    private $add;
    private $update;
    private $delete;

    public function __construct()
    {
        $this->people = new People();
        $this->add = "La persona se agregó correctamente id ";
        $this->update = "Se actualizo correctamente el registro con el id ";
        $this->delete = "Se eliminó el registro con el id ";
        $this->error = "No se encontro el registro con el id ";

    }

    /**
     * @return \Illuminate\Http\JsonResponse
     *
     * Retorna toda la lista de personas de Starwars que se encuentran en la tabla people
     */
    public function list()
    {
        return response()->json($this->people::SELECT("*")
            ->orderByDesc("id")
            ->get());
    }

    /**
     * @param Request $request
     * @return \Illuminate\Http\JsonResponse
     *
     * Crear un nuevo registro de personas en la base de datos
     */
    public function create(Request $request)
    {
        try {

            $person = People::create($request->all());
            return response()->json([
                'status' => '200',
                'msg' => 'success',
                'details' => $this->add . $person->id
            ]);

        } catch (Exception $e) {
            echo 'Message: ' . $e->getMessage();
        }


    }

    /**
     * @param Request $request
     * @return \Illuminate\Http\JsonResponse
     *
     * Mostrar solo el registro de una persona basado en el id como parametro de busqueda.
     */

    public function listOne(Request $request)

    {
        try {
            return response()->json($this->people::find($request->id));
        } catch (Exception $e) {
            echo 'Message: ' . $e->getMessage();
        }
    }

    /**
     * @param Request $request
     * @return \Illuminate\Http\JsonResponse
     *  Actualizar una persona de la lista basado en el id
     */

    public function update(Request $request)
    {
        try {

            $person = $this->people::find($request->id);
            if ($person) {

                $person->update($request->all());
                return response()->json([
                    'status' => '200',
                    'msg' => 'success',
                    'details' => $this->update . $request->id
                ]);
            } else {

                return response()->json([
                    'status' => '250',
                    'msg' => 'error',
                    'details' => $this->error . $request->id
                ]);


            }

        } catch (Exception $e) {
            echo 'Message: ' . $e->getMessage();
        }

    }

    /**
     * @param Request $request
     * @return \Illuminate\Http\JsonResponse
     *
     * Eliminar una persona de la lista basado en el id
     */

    public function delete(Request $request)
    {

        try {
            $person = people::destroy($request->id);
            if ($person) {
                return response()->json([
                    'status' => '200',
                    'msg' => 'success',
                    'details' => $this->delete . $person->id
                ]);
            } else {
                return response()->json([
                    'status' => '250',
                    'msg' => 'error',
                    'details' => $this->error . $person->id
                ]);
            }

        } catch (Exception $e) {
            echo 'Message: ' . $e->getMessage();
        }

    }

}
