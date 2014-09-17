
require 'rspec'
require "TP1/Metaprogramming"

describe 'Let Constructors build Prototypes' do

  before(:all) do
    @guerrero = TP::PrototypedObject.new
    @guerrero.set_property(:energia, 0)
    @guerrero.set_property(:potencial_defensivo, 10)
    @guerrero.set_property(:potencial_ofensivo, 10)
  end

  it 'should set a new constructor based on a prototype' do

    Guerrero = TP::PrototypedConstructor.new(@guerrero, proc {
        |guerrero_nuevo, una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      guerrero_nuevo.energia = una_energia
      guerrero_nuevo.potencial_ofensivo = un_potencial_ofensivo
      guerrero_nuevo.potencial_defensivo = un_potencial_defensivo
    })

    un_guerrero = Guerrero.new(100, 30, 10)
    expect(un_guerrero.energia).to eq(100)
    expect(un_guerrero.instance_module_variable_get(:energia)).to eq(100)
  end

  it 'should set a new constructor with a hash' do

    Guerrero = TP::PrototypedConstructor.new(@guerrero)
    un_guerrero = Guerrero.new({energia: 100, potencial_ofensivo: 30, potencial_defensivo: 10})
    expect(un_guerrero.potencial_ofensivo).to eq(30)

  end

  it 'should copy prototype state' do

    Guerrero = TP::PrototypedConstructor.copy(@guerrero)
    un_guerrero = Guerrero.new
    expect(un_guerrero.potencial_defensivo).to eq(10)

  end

  it 'should keep different contexts for every copy' do

    Guerrero = TP::PrototypedConstructor.copy(@guerrero)
    un_guerrero = Guerrero.new
    otro_guerrero = Guerrero.new

    otro_guerrero.energia = 1500
    otro_guerrero.potencial_defensivo = 102
    un_guerrero.potencial_defensivo = 40

    expect(un_guerrero.potencial_defensivo).to eq(40)
    expect(otro_guerrero.potencial_defensivo).to eq(102)
    expect(otro_guerrero.energia).to eq(1500)

  end

  it 'should set a builder from another' do

    Guerrero = TP::PrototypedConstructor.new(@guerrero, proc {
        |guerrero_nuevo, una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      guerrero_nuevo.energia = una_energia
      guerrero_nuevo.potencial_ofensivo = un_potencial_ofensivo
      guerrero_nuevo.potencial_defensivo = un_potencial_defensivo
    })

    Espadachin = Guerrero.extended {
        |espadachin, habilidad, potencial_espada|
        espadachin.set_property(:habilidad, habilidad)
        espadachin.set_property(:potencial_espada, potencial_espada)
        espadachin.set_method(:potencial_ofensivo, proc {
        @potencial_ofensivo + self.potencial_espada * self.habilidad
      })
    }

    espadachin = Espadachin.new(100, 30, 10, 0.5, 30)
    expect(espadachin.potencial_ofensivo).to eq(45)
  end

end