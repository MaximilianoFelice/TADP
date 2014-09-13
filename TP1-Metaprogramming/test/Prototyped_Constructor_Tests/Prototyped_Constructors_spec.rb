require 'rspec'
require "TP1/Metaprogramming"

describe 'Let Constructors build Prototypes' do

  it 'should set a new constructor' do

    guerrero = Object.new
    guerrero.extend TP::Prototyped

    Guerrero = TP::ProtoConstructor.new(guerrero, proc {
        |guerrero_nuevo, una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      guerrero_nuevo.energia = una_energia
      guerrero_nuevo.potencial_ofensivo = un_potencial_ofensivo
      guerrero_nuevo.potencial_defensivo = un_potencial_defensivo
    })

    un_guerrero = Guerrero.new(100, 30, 10)
    expect(un_guerrero.energia).to eq(100)

  end

end