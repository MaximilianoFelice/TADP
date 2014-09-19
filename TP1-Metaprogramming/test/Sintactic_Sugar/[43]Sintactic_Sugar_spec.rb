require 'rspec'
require "TP1/Metaprogramming"

describe 'Respect Sintactic Sugar' do

  it 'should do something' do

    guerrero_proto = TP::PrototypedObject.new

    guerrero_proto.energia = 100
    expect(guerrero_proto.energia).to eq(100)

    guerrero_proto.potencial_defensivo = 10
    guerrero_proto.potencial_ofensivo = 30

    guerrero_proto.atacar_a_ = proc{|otro_guerrero|
      if (otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
        otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
      end
    }

    guerrero_proto.recibe_danio_ = proc { |value| self.energia -= value}

    Guerrero = TP::PrototypedConstructor.copy(guerrero_proto)
    un_guerrero = Guerrero.new

    Guerrero.new.atacar_a(un_guerrero)
    expect(un_guerrero.energia).to eq(80)

  end
end