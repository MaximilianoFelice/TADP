require 'rspec'
require "TP1/Metaprogramming"

describe 'Respect Sintactic Sugar' do

  it 'should build prototypes values dynamically' do

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

  it 'should build object from a block' do

    guerrero_proto = TP::PrototypedObject.new_prototype {
      self.energia = 100
      self.potencial_ofensivo = 30
      self.potencial_defensivo = 10
      self.atacar_a_ = proc{|otro_guerrero|
        if (otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio_ = proc{ |value| self.energia -= value}
    }
    expect(guerrero_proto.potencial_ofensivo).to eq(30)

    Guerrero = TP::PrototypedConstructor.copy(guerrero_proto)
    un_guerrero = Guerrero.new

    Guerrero.new.atacar_a(un_guerrero)
    expect(un_guerrero.energia).to eq(80)
  end

  it 'Should set new prototyped constructors builders' do
    @guerrero = Object.new
    @guerrero.extend TP::Prototyped

    Guerrero = TP::PrototypedConstructor.new(@guerrero) do |una_energia, un_potencial_ofensivo,un_potencial_defensivo|
        self.energia = una_energia
        self.potencial_ofensivo = un_potencial_ofensivo
        self.potencial_defensivo = un_potencial_defensivo
    end

    un_warro = Guerrero.new(10, 30, 50)

    expect(un_warro.potencial_ofensivo).to eq(30)
  end

  it 'should create builders with new syntax' do
    Guerrero = TP::PrototypedConstructor.create {
      self.atacar_a_ = proc{|otro_guerrero|
        if (otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio_ = proc{ |value| self.energia -= value}
    }.with {
        |una_energia, un_potencial_ofensivo, un_potencial_defensivo|
      self.energia = una_energia
      self.potencial_ofensivo = un_potencial_ofensivo
      self.potencial_defensivo = un_potencial_defensivo
    }

    new_warro = Guerrero.new(10, 100, 0.66)

    expect(new_warro.energia).to eq(10)
  end

  it 'should add properties' do
    Guerrero = TP::PrototypedConstructor.create {
      self.atacar_a_ = proc{|otro_guerrero|
        if (otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio_ = proc{ |value| self.energia -= value}
    }.with_properties([:energia, :potencial_ofensivo, :potencial_defensivo])

    new_warro = Guerrero.new()

    expect(new_warro.respond_to?(:energia)).to eq(true)
  end

  it 'should use new syntax in constructors' do
    Guerrero = TP::PrototypedConstructor.create {
      self.atacar_a_ = proc{|otro_guerrero|
        if (otro_guerrero.potencial_defensivo < self.potencial_ofensivo)
          otro_guerrero.recibe_danio(self.potencial_ofensivo - otro_guerrero.potencial_defensivo)
        end
      }
      self.recibe_danio_ = proc{ |value| self.energia -= value}
    }.with_properties([:energia, :potencial_ofensivo, :potencial_defensivo])

    Espadachin = Guerrero.extended {
        |una_habilidad, un_potencial_espada|
      self.habilidad = una_habilidad
      self.potencial_espada = un_potencial_espada
      self.potencial_ofensivo_ = proc {
        @potencial_ofensivo + self.potencial_espada * self.habilidad
      }
    }

    new_espa = Espadachin.new(10, 50)

    expect(new_espa.habilidad).to eq(10)
    expect(new_espa.potencial_espada).to eq(50)
    expect(new_espa.respond_to?(:potencial_ofensivo=)).to eq(true)
    new_espa.potencial_ofensivo = 100
    expect(new_espa.potencial_ofensivo).to eq(100 + 50 * 10)
  end

end