require 'rspec'
require "TP1/Prototyped"
require "TP1/Metaprogramming"

describe 'Hierarchy Redirection' do

  before (:all) do
    @nuevoObjeto = TP::PrototypedObject.new
    @nuevoObjetoPrototipado = TP::PrototypedObject.new
  end

  it 'should set a new prototype' do

    @nuevoObjetoPrototipado.set_prototype(@nuevoObjeto)

    @nuevoObjeto.define_singleton_method(:devolver, lambda{50})

    expect(@nuevoObjetoPrototipado.devolver).to eq(50)

  end

  it 'should redefine previous behaviour' do

    @nuevoObjetoPrototipado.define_singleton_method(:devolver, lambda{180})

    expect(@nuevoObjeto.devolver).to eq(50)
    expect(@nuevoObjetoPrototipado.devolver).to eq(180)

  end

end

