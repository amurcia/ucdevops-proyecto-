<?php


namespace App\Models;

use Illuminate\Database\Eloquent\Model;


class People
{
    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */

    protected $table = 'people';

    protected $fillable = [
        'name',
        'height',
        'mass',
        'hair_color',
        'skin_color',
        'eye_color',
        'birth_year',
        'gender',
    ];

    /**
     * The attributes excluded from the model's JSON form.
     *
     * @var array
     */
    protected $hidden = [
        'password',
    ];

}
